package br.com.pedidos_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDisponibilidadeRequest(
        @NotNull(message = "ID do Produto é obrigatório")
        Long idProduto,
        @NotNull(message = "Quantidade do produto é obrigatória")
        BigDecimal quantidade
) {
}