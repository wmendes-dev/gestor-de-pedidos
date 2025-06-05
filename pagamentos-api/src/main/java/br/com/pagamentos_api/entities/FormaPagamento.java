package br.com.pagamentos_api.entities;

import br.com.pagamentos_api.dtos.request.PagamentoRequest;
import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_FORMA_PAGAMENTO")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FORMA_PAGAMENTO")
    private Long idFormaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "METODO_PAGAMENTO", nullable = false)
    private MetodoPagamentoEnum metodoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANDEIRA_CARTAO")
    private BandeiraCartaoEnum bandeiraCartao;

    @Column(name = "QUANTIDADE_PARCELAS", nullable = false)
    private Integer quantidadeParcelas;

    public FormaPagamento() {
    }

    public FormaPagamento(PagamentoRequest pagamentoRequest) {
        this.metodoPagamento = pagamentoRequest.metodoPagamento();
        this.bandeiraCartao = pagamentoRequest.bandeiraCartao();
        this.quantidadeParcelas = pagamentoRequest.quantidadeParcelas();
    }

}