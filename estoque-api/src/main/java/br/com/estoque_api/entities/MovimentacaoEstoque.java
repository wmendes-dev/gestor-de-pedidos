package br.com.estoque_api.entities;

import br.com.estoque_api.enums.SituacaoMovimentacaoEstoqueEnum;
import br.com.estoque_api.enums.TipoMovimentacaoEstoqueEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_MOVIMENTACAO_ESTOQUE")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIMENTACAO_ESTOQUE")
    private Long idMovimentacaoEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TipoMovimentacaoEstoqueEnum tipoMovimentacao;

    @OneToOne
    @JoinColumn(name = "ID_RESERVA_ESTOQUE")
    private ReservaEstoque reservaEstoque;

    @OneToMany(mappedBy = "movimentacaoEstoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoMovimentacaoEstoque> produtosMovimentacaoEstoque = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoMovimentacaoEstoqueEnum situacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    public MovimentacaoEstoque() {
        this.situacao = SituacaoMovimentacaoEstoqueEnum.CRIADA;
        this.dataCriacao = LocalDateTime.now();
    }

    public MovimentacaoEstoque(ReservaEstoque reservaEstoque) {
        this();
        this.tipoMovimentacao = TipoMovimentacaoEstoqueEnum.PEDIDO;
        this.reservaEstoque = reservaEstoque;
        this.produtosMovimentacaoEstoque = reservaEstoque.getProdutosReservaEstoque()
                .stream()
                .map(produtoReservaEstoque -> new ProdutoMovimentacaoEstoque(produtoReservaEstoque, this))
                .toList();
    }

}
