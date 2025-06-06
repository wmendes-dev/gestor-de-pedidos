package br.com.pedidos_api.dtos.response;

public record ResumoUsuarioResponse(
        Long idUsuario,
        String cpf,
        String nomeCompleto
) {
}
