package br.com.pedidos_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResumoClienteRequest(
        @NotNull(message = "ID do cliente é obrigatório")
        Long idCliente,
        @NotBlank(message = "CPF do cliente é obrigatório")
        String cpf,
        @NotBlank(message = "Nome do cliente é obrigatório")
        String nome,
        @NotBlank(message = "E-mail do cliente é obrigatório")
        String email
) {
}