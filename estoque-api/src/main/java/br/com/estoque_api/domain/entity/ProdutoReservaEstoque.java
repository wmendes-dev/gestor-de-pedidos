package br.com.estoque_api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_PRODUTO_RESERVA_ESTOQUE")
public class ProdutoReservaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_RESERVA_ESTOQUE")
    private Long idProdutoReservaEstoque;

    @Column(name = "QUANTIDADE_RESERVADA", nullable = false)
    private BigDecimal quantidadeReservada;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_RESERVA_ESTOQUE", nullable = false)
    private ReservaEstoque reservaEstoque;

}
