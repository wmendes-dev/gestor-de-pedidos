package br.com.pedidos_api.entities;

import br.com.pedidos_api.dtos.request.ResumoClienteRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ResumoCliente {

    @Column(name = "ID_CLIENTE", nullable = false)
    private Long idCliente;

    @Column(name = "CPF_CLIENTE", nullable = false)
    private String cpf;

    @Column(name = "NOME_CLIENTE", nullable = false)
    private String nome;

    @Column(name = "EMAIL_CLIENTE", nullable = false)
    private String email;

    public ResumoCliente(ResumoClienteRequest resumoClienteRequest) {
        this.idCliente = resumoClienteRequest.idCliente();
        this.cpf = resumoClienteRequest.cpf();
        this.nome = resumoClienteRequest.nome();
        this.email = resumoClienteRequest.email();
    }

}
