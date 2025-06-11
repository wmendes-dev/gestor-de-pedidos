package br.com.estoque_api.domain.dto.event;

import java.util.List;

public record PedidoCriadoEvent(
        Long idPedido,
        ResumoFormaPagamentoEvent formaPagamento,
        List<ProdutoPedidoEvent> produtosPedido
) {
}
