package br.com.autenticacao_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "CPF é obrigatório")
        String cpf,
        @NotNull(message = "Senha é obrigatória")
        String senha
) {
}
