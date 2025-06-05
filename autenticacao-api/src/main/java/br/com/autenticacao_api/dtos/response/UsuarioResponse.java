package br.com.autenticacao_api.dtos.response;

public record UsuarioResponse(
        Long idUsuario,
        String cpf,
        String nomeCompleto
) {
}
