package br.com.pedidos_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResumoUsuarioRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        Long idUsuario,
        @NotBlank(message = "CPF do usuário é obrigatório")
        String cpf,
        @NotBlank(message = "Nome do usuário é obrigatório")
        String nomeCompleto
) {
}