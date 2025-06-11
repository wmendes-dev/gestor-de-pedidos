package br.com.pedidos_api.domain.entity;

import br.com.pedidos_api.domain.dto.request.ResumoProdutoRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ResumoProduto {

    @Column(name = "ID_PRODUTO", nullable = false)
    private Long idProduto;

    @Column(name = "NOME_PRODUTO", nullable = false)
    private String nome;

    public ResumoProduto(ResumoProdutoRequest resumoProdutoRequest) {
        this.idProduto = resumoProdutoRequest.idProduto();
        this.nome = resumoProdutoRequest.nome();
    }

}
