package br.com.pedidos_api.domain.dto.response;

public record ResumoUsuarioResponse(
        Long idUsuario,
        String cpf,
        String nomeCompleto
) {
}
