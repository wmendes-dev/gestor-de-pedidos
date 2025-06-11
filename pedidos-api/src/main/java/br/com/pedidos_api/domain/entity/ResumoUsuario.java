package br.com.pedidos_api.domain.entity;

import br.com.pedidos_api.domain.dto.request.ResumoUsuarioRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ResumoUsuario {

    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "CPF_USUARIO", nullable = false)
    private String cpf;

    @Column(name = "NOME_USUARIO", nullable = false)
    private String nomeCompleto;

    public ResumoUsuario(ResumoUsuarioRequest resumoUsuarioRequest) {
        this.idUsuario = resumoUsuarioRequest.idUsuario();
        this.cpf = resumoUsuarioRequest.cpf();
        this.nomeCompleto = resumoUsuarioRequest.nomeCompleto();
    }

}
