package br.com.estoque_api.dtos.event;

import java.util.List;

public record PedidoCriadoEvent(
        Long idPedido,
        List<ProdutoPedidoEvent> produtosPedido
) {
}
