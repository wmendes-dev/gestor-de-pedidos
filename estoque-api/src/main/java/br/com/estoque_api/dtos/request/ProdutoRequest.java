package br.com.estoque_api.dtos.request;

import br.com.estoque_api.enums.SituacaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotNull
        SituacaoEnum situacao,
        @NotBlank
        String nome,
        @NotNull
        BigDecimal valor
) {
}