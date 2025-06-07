package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.Pedido;

import java.util.List;

public record PedidoCriadoEvent(
        Long idPedido,
        ResumoFormaPagamentoEvent formaPagamento,
        List<ProdutoPedidoEvent> produtosPedido
) {

    public PedidoCriadoEvent(Pedido pedido) {
        this(
                pedido.getIdPedido(),
                new ResumoFormaPagamentoEvent(pedido.getFormaPagamento()),
                pedido.getProdutosPedido().stream()
                        .map(ProdutoPedidoEvent::new)
                        .toList()
        );
    }

}
