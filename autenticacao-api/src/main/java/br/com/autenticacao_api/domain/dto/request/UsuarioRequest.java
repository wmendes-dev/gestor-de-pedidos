package br.com.autenticacao_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotNull(message = "CPF é obrigatório")
        String cpf,
        @NotNull(message = "Senha é obrigatória")
        String senha,
        @NotNull(message = "Nome completo é obrigatório")
        String nomeCompleto
) {
}
