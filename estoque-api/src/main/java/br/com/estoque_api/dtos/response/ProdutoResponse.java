package br.com.estoque_api.dtos.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long idProduto,
        String nome,
        BigDecimal valor,
        BigDecimal quantidadeDisponivel
) {
}