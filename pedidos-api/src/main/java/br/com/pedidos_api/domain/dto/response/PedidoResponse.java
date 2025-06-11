package br.com.pedidos_api.domain.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        Long idPedido,
        BigDecimal valorSubTotal,
        BigDecimal valorDesconto,
        BigDecimal valorTotal,
        ResumoUsuarioResponse usuario,
        List<ProdutoPedidoResponse> produtosPedido
) {
}