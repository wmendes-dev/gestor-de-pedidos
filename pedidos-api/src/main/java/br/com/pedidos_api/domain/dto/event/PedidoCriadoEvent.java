package br.com.pedidos_api.domain.dto.event;

import br.com.pedidos_api.domain.entity.Pedido;

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
