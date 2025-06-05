package br.com.pedidos_api.dtos.response;

import java.math.BigDecimal;

public record ProdutoPedidoResponse(
        Long idProdutoPedido,
        BigDecimal quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        ResumoProdutoResponse produto
) {
}