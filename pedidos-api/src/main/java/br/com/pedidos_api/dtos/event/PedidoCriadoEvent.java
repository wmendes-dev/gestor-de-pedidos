package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.Pedido;

import java.util.List;

public record PedidoCriadoEvent(
        Long idPedido,
        List<ProdutoPedidoEvent> produtosPedido
) {

    public PedidoCriadoEvent(Pedido pedido) {
        this(
                pedido.getIdPedido(),
                pedido.getProdutosPedido().stream()
                        .map(ProdutoPedidoEvent::new)
                        .toList()
        );
    }

}
