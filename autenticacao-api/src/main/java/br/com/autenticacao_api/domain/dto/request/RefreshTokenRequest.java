package br.com.autenticacao_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull(message = "refreshToken é obrigatório")
        String refreshToken
) {
}
