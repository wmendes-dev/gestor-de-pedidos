package br.com.autenticacao_api.dtos.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        long accessTokenExp,
        long refreshTokenExp,
        UsuarioResponse usuario
) {
}
