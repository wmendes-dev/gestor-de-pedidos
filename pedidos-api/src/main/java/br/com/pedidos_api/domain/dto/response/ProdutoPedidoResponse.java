package br.com.pedidos_api.domain.dto.response;

import java.math.BigDecimal;

public record ProdutoPedidoResponse(
        Long idProdutoPedido,
        BigDecimal quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        ResumoProdutoResponse produto
) {
}