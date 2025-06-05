package br.com.estoque_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_PRODUTO_MOVIMENTACAO_ESTOQUE")
public class ProdutoMovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_MOVIMENTACAO_ESTOQUE")
    private Long idProdutoMovimentacaoEstoque;

    @Column(name = "QUANTIDADE", nullable = false)
    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_MOVIMENTACAO_ESTOQUE", nullable = false)
    private MovimentacaoEstoque movimentacaoEstoque;

    public ProdutoMovimentacaoEstoque() {
    }

    public ProdutoMovimentacaoEstoque(ProdutoReservaEstoque produtoReservaEstoque, MovimentacaoEstoque movimentacaoEstoque) {
        this.quantidade = produtoReservaEstoque.getQuantidadeReservada();
        this.produto = produtoReservaEstoque.getProduto();
        this.movimentacaoEstoque = movimentacaoEstoque;
    }

}
