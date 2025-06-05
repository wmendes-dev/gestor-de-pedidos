package br.com.pedidos_api.dtos.response;

public record ResumoClienteResponse(
        Long idCliente,
        String cpf,
        String nome,
        String email
) {
}
