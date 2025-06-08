package br.com.estoque_api.services;

import br.com.estoque_api.dtos.event.*;
import br.com.estoque_api.entities.MovimentacaoEstoque;
import br.com.estoque_api.entities.Produto;
import br.com.estoque_api.entities.ProdutoReservaEstoque;
import br.com.estoque_api.entities.ReservaEstoque;
import br.com.estoque_api.enums.TipoEventoEnum;
import br.com.estoque_api.exceptions.EntidadeNaoEncontradaException;
import br.com.estoque_api.exceptions.NegocioException;
import br.com.estoque_api.exceptions.ProdutoIndisponivelNoEstoqueException;
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
            try {
                Produto produto = validarDisponibilidadeDoProduto(produtoPedidoEvent);
                atualizarQuantidadeDisponivelDoProduto(produto, produtoPedidoEvent.quantidade());
            } catch (NegocioException e) {
                tratarErroDeNegocioAoReservarEstoque(pedidoCriadoEvent.idPedido(), e);
                return;
            }
        }

        ReservaEstoque reservaEstoque = criarReservaEstoque(pedidoCriadoEvent);
        this.eventoOutboxService.criarEvento(
                new EstoqueReservadoEvent(pedidoCriadoEvent),
                TipoEventoEnum.ESTOQUE_RESERVADO,
                reservaEstoque.getIdReservaEstoque().toString());
    }

    private Produto validarDisponibilidadeDoProduto(ProdutoPedidoEvent produtoPedidoEvent) {
        Produto produto = this.produtoService.obterProdutoPorId(produtoPedidoEvent.idProduto());

        BigDecimal quantidadeSolicitada = produtoPedidoEvent.quantidade();
        BigDecimal quantidadeDisponivel = produto.getQuantidadeDisponivel();

        if (quantidadeDisponivel.compareTo(quantidadeSolicitada) < 0) {
            throw new ProdutoIndisponivelNoEstoqueException(produto.getNome(), produto.getIdProduto(), quantidadeSolicitada, quantidadeDisponivel);
        }

        return produto;
    }

    private void atualizarQuantidadeDisponivelDoProduto(Produto produto, BigDecimal quantidadeReservada) {
        BigDecimal novaQuantidadeDisponivel = produto.getQuantidadeDisponivel().subtract(quantidadeReservada);
        this.produtoService.atualizarQuantidadeDisponivel(produto, novaQuantidadeDisponivel);
    }

    private void tratarErroDeNegocioAoReservarEstoque(Long idPedido, NegocioException e) {
        if (e instanceof ProdutoIndisponivelNoEstoqueException ex) {
            this.eventoOutboxService.criarEvento(
                    new ProdutoIndisponivelEvent(idPedido, ex.getMessage()),
                    TipoEventoEnum.PRODUTO_INDISPONIVEL,
                    idPedido.toString());
            return;
        }

        if (e instanceof EntidadeNaoEncontradaException ex) {
            this.eventoOutboxService.criarEvento(
                    new ProdutoNaoEncontradoEvent(idPedido, ex.getMessage()),
                    TipoEventoEnum.PRODUTO_NAO_ENCONTRADO,
                    idPedido.toString());
        }
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
