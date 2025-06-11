package br.com.pagamentos_api.domain.entity;

import br.com.pagamentos_api.domain.enums.SituacaoAnalisePagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_ANALISE_PAGAMENTO")
public class AnalisePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANALISE_PAGAMENTO")
    private Long idAnalisePagamento;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "NUMERO_TENTATIVA", nullable = false)
    private Integer numeroTentativa;

    @Column(name = "ID_PEDIDO", nullable = false)
    private Long idPedido;

    @OneToOne
    @JoinColumn(name = "ID_FORMA_PAGAMENTO", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoAnalisePagamentoEnum situacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    public AnalisePagamento() {
        this.numeroTentativa = 1;
        this.situacao = SituacaoAnalisePagamentoEnum.EM_PROCESSAMENTO;
        this.dataCriacao = LocalDateTime.now();
    }

}