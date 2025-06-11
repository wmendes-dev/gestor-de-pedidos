package br.com.pagamentos_api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_PAGAMENTO_APROVADO")
public class PagamentoAprovado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGAMENTO_APROVADO")
    private Long idPagamentoAprovado;

    @Column(name = "ID_PAGAMENTO", nullable = false)
    private String idPagamento;

    @Column(name = "DATA_APROVACAO", nullable = false)
    private LocalDateTime dataAprovacao;

    @ManyToOne
    @JoinColumn(name = "ID_ANALISE_PAGAMENTO", nullable = false)
    private AnalisePagamento analisePagamento;

}