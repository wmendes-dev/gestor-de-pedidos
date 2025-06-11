package br.com.estoque_api.domain.entity;

import br.com.estoque_api.domain.enums.SituacaoReservaEstoqueEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_RESERVA_ESTOQUE")
public class ReservaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA_ESTOQUE")
    private Long idReservaEstoque;

    @Column(name = "ID_PEDIDO", nullable = false)
    private Long idPedido;

    @OneToMany(mappedBy = "reservaEstoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoReservaEstoque> produtosReservaEstoque = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoReservaEstoqueEnum situacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    public ReservaEstoque() {
        this.situacao = SituacaoReservaEstoqueEnum.CRIADA;
        this.dataCriacao = LocalDateTime.now();
    }

    public ReservaEstoque(Long idPedido) {
        this();
        this.idPedido = idPedido;
    }

}
