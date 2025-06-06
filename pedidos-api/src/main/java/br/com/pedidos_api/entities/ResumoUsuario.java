package br.com.pedidos_api.entities;

import br.com.pedidos_api.dtos.request.ResumoUsuarioRequest;
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

    public ResumoUsuario(ResumoUsuarioRequest resumoUsuario) {
        this.idUsuario = resumoUsuario.idUsuario();
        this.cpf = resumoUsuario.cpf();
        this.nomeCompleto = resumoUsuario.nomeCompleto();
    }

}
