package br.com.estoque_api.services;

import br.com.estoque_api.dtos.event.*;
import br.com.estoque_api.entities.MovimentacaoEstoque;
import br.com.estoque_api.entities.Produto;
import br.com.estoque_api.entities.ProdutoReservaEstoque;
import br.com.estoque_api.entities.ReservaEstoque;
import br.com.estoque_api.enums.TipoEventoEnum;
import br.com.estoque_api.exceptions.EntidadeNaoEncontradaException;
import br.com.estoque_api.exceptions.NegocioException;
import br.com.estoque_api.exceptions.ProdutoIndisponivelException;
import br.com.estoque_api.repositories.MovimentacaoEstoqueRepository;
import br.com.estoque_api.repositories.ReservaEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<ErroProdutoEvent> erroProdutoEventList = new ArrayList<>();
        for (ProdutoPedidoEvent produtoPedidoEvent : pedidoCriadoEvent.produtosPedido()) {
            try {
                Produto produto = validarDisponibilidadeDoProduto(produtoPedidoEvent);
                atualizarQuantidadeDisponivelDoProduto(produto, produtoPedidoEvent.quantidade());
            } catch (NegocioException e) {
                ErroProdutoEvent erroProdutoEvent = tratarErroDeNegocioAoReservarEstoque(produtoPedidoEvent.produto(), e);
                erroProdutoEventList.add(erroProdutoEvent);
            }
        }

        if (!CollectionUtils.isEmpty(erroProdutoEventList)) {
            this.eventoOutboxService.criarEvento(
                    new ErroReservaEstoqueEvent(pedidoCriadoEvent.idPedido(), erroProdutoEventList),
                    TipoEventoEnum.ERRO_RESERVA_ESTOQUE,
                    pedidoCriadoEvent.idPedido().toString());
            return;
        }

        ReservaEstoque reservaEstoque = criarReservaEstoque(pedidoCriadoEvent);
        this.eventoOutboxService.criarEvento(
                new EstoqueReservadoEvent(pedidoCriadoEvent),
                TipoEventoEnum.ESTOQUE_RESERVADO,
                reservaEstoque.getIdReservaEstoque().toString());
    }

    private Produto validarDisponibilidadeDoProduto(ProdutoPedidoEvent produtoPedidoEvent) {
        Produto produto = this.produtoService.obterProdutoPorId(produtoPedidoEvent.produto().idProduto());

        BigDecimal quantidadeSolicitada = produtoPedidoEvent.quantidade();
        BigDecimal quantidadeDisponivel = produto.getQuantidadeDisponivel();

        if (quantidadeDisponivel.compareTo(quantidadeSolicitada) < 0) {
            throw new ProdutoIndisponivelException();
        }

        return produto;
    }

    private void atualizarQuantidadeDisponivelDoProduto(Produto produto, BigDecimal quantidadeReservada) {
        BigDecimal novaQuantidadeDisponivel = produto.getQuantidadeDisponivel().subtract(quantidadeReservada);
        this.produtoService.atualizarQuantidadeDisponivel(produto, novaQuantidadeDisponivel);
    }

    private ErroProdutoEvent tratarErroDeNegocioAoReservarEstoque(ResumoProdutoEvent resumoProdutoEvent, NegocioException e) {
        Map<Class<? extends NegocioException>, String> movivoErroMap = Map.of(
                ProdutoIndisponivelException.class, "Produto indisponível no momento",
                EntidadeNaoEncontradaException.class, "Produto não encontrado"
        );

        String motivoErro = movivoErroMap.getOrDefault(e.getClass(), "Erro inesperado ao reservar estoque");
        return new ErroProdutoEvent(resumoProdutoEvent.idProduto(), resumoProdutoEvent.nome(), motivoErro);
    }

    private ReservaEstoque criarReservaEstoque(PedidoCriadoEvent pedidoCriadoEvent) {
        ReservaEstoque reservaEstoque = new ReservaEstoque(pedidoCriadoEvent.idPedido());

        for (ProdutoPedidoEvent produtoPedidoEvent : pedidoCriadoEvent.produtosPedido()) {
            Produto produto = this.produtoService.obterProdutoPorId(produtoPedidoEvent.produto().idProduto());

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
