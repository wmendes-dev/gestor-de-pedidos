package br.com.pedidos_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoPedidoRequest(
        Long idProdutoPedido,
        @NotNull(message = "Quantidade do produto é obrigatória")
        BigDecimal quantidade,
        @NotNull(message = "Valor unitário do produto é obrigatório")
        BigDecimal valorUnitario,
        @NotNull(message = "Valor total do produto é obrigatório")
        BigDecimal valorTotal,
        @NotNull(message = "Produto é obrigatório")
        ResumoProdutoRequest produto
) {
}