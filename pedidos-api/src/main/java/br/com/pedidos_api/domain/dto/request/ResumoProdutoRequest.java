package br.com.pedidos_api.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResumoProdutoRequest(
        @NotNull(message = "ID do produto é obrigatório")
        Long idProduto,
        @NotBlank(message = "Nome do produto é obrigatório")
        String nome
) {
}