package br.com.estoque_api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long idProduto;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Column(name = "QUANTIDADE_DISPONIVEL", nullable = false)
    private BigDecimal quantidadeDisponivel;

    public Produto(Long idProduto) {
        this.idProduto = idProduto;
    }

}
