package br.com.estoque_api.services;

import br.com.estoque_api.dtos.event.EstoqueReservadoEvent;
import br.com.estoque_api.dtos.event.PedidoCriadoEvent;
import br.com.estoque_api.dtos.event.ProdutoPedidoEvent;
import br.com.estoque_api.entities.MovimentacaoEstoque;
import br.com.estoque_api.entities.Produto;
import br.com.estoque_api.entities.ProdutoReservaEstoque;
import br.com.estoque_api.entities.ReservaEstoque;
import br.com.estoque_api.enums.TipoEventoEnum;
import br.com.estoque_api.exceptions.EntidadeNaoEncontradaException;
import br.com.estoque_api.repositories.MovimentacaoEstoqueRepository;
import br.com.estoque_api.repositories.ReservaEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final ProdutoService produtoService;

    private final ReservaEstoqueRepository reservaEstoqueRepository;

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    private final EventoOutboxService eventoOutboxService;

    @Transactional
    public void reservarEstoque(PedidoCriadoEvent pedidoCriadoEvent) {
        if (CollectionUtils.isEmpty(pedidoCriadoEvent.produtosPedido())) return;

        for (ProdutoPedidoEvent produtoPedidoEvent : pedidoCriadoEvent.produtosPedido()) {
            Produto produto = validarDisponibilidade(produtoPedidoEvent);
            atualizarQuantidadeDisponivel(produto, produtoPedidoEvent.quantidade());
        }

        ReservaEstoque reservaEstoque = criarReservaEstoque(pedidoCriadoEvent);
        this.eventoOutboxService.criarEvento(
                new EstoqueReservadoEvent(pedidoCriadoEvent),
                TipoEventoEnum.ESTOQUE_RESERVADO,
                reservaEstoque.getIdReservaEstoque().toString());
    }

    private Produto validarDisponibilidade(ProdutoPedidoEvent produtoPedidoEvent) {
        Produto produto = this.produtoService.obterProdutoPorId(produtoPedidoEvent.idProduto());

        BigDecimal quantidadeSolicitada = produtoPedidoEvent.quantidade();
        BigDecimal quantidadeDisponivel = produto.getQuantidadeDisponivel();

        if (quantidadeDisponivel.compareTo(quantidadeSolicitada) < 0) {
            throw new IllegalStateException("Produto indisponível para a quantidade solicitada.");
        }

        return produto;
    }

    private void atualizarQuantidadeDisponivel(Produto produto, BigDecimal quantidadeReservada) {
        BigDecimal novaQuantidadeDisponivel = produto.getQuantidadeDisponivel().subtract(quantidadeReservada);
        this.produtoService.atualizarQuantidadeDisponivel(produto, novaQuantidadeDisponivel);
    }

    private ReservaEstoque criarReservaEstoque(PedidoCriadoEvent pedidoCriadoEvent) {
        ReservaEstoque reservaEstoque = new ReservaEstoque(pedidoCriadoEvent.idPedido());

        for (ProdutoPedidoEvent produtoPedidoEvent : pedidoCriadoEvent.produtosPedido()) {
            Produto produto = this.produtoService.obterProdutoPorId(produtoPedidoEvent.idProduto());

            ProdutoReservaEstoque produtoReservaEstoque = new ProdutoReservaEstoque();
            produtoReservaEstoque.setQuantidadeReservada(produtoPedidoEvent.quantidade());
            produtoReservaEstoque.setProduto(produto);
            produtoReservaEstoque.setReservaEstoque(reservaEstoque);
            reservaEstoque.getProdutosReservaEstoque().add(produtoReservaEstoque);
        }

        return this.reservaEstoqueRepository.save(reservaEstoque);
    }

    @Transactional
    public void movimentarEstoque(Long idPedido) {
        ReservaEstoque reservaEstoque = this.reservaEstoqueRepository.findByIdPedido(idPedido)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Reserva de estoque não encontrada", idPedido));

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque(reservaEstoque);
        movimentacaoEstoque = this.movimentacaoEstoqueRepository.save(movimentacaoEstoque);
        this.eventoOutboxService.criarEvento(
                idPedido,
                TipoEventoEnum.ESTOQUE_MOVIMENTADO,
                movimentacaoEstoque.getIdMovimentacaoEstoque().toString());
    }

}
