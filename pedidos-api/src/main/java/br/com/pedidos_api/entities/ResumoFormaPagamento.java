package br.com.pedidos_api.entities;

import br.com.pedidos_api.dtos.request.ResumoFormaPagamentoRequest;
import br.com.pedidos_api.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.enums.MetodoPagamentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Embeddable
public class ResumoFormaPagamento {

    @Column(name = "VALOR_PAGAMENTO", nullable = false)
    private BigDecimal valorPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "METODO_PAGAMENTO", nullable = false)
    private MetodoPagamentoEnum metodoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANDEIRA_CARTAO")
    private BandeiraCartaoEnum bandeiraCartao;

    @Column(name = "QUANTIDADE_PARCELAS", nullable = false)
    private Integer quantidadeParcelas;

    public ResumoFormaPagamento(ResumoFormaPagamentoRequest resumoFormaPagamentoRequest) {
        this.valorPagamento = resumoFormaPagamentoRequest.valorPagamento();
        this.metodoPagamento = resumoFormaPagamentoRequest.metodoPagamento();
        this.bandeiraCartao = resumoFormaPagamentoRequest.bandeiraCartao();
        this.quantidadeParcelas = resumoFormaPagamentoRequest.quantidadeParcelas();
    }

}
