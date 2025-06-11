package br.com.autenticacao_api.domain.dto.response;

public record UsuarioResponse(
        Long idUsuario,
        String cpf,
        String nomeCompleto
) {
}
