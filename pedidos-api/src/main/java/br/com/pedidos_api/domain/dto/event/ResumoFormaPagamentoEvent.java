package br.com.pedidos_api.domain.dto.event;

import br.com.pedidos_api.domain.entity.ResumoFormaPagamento;
import br.com.pedidos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.domain.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record ResumoFormaPagamentoEvent(
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {

    public ResumoFormaPagamentoEvent(ResumoFormaPagamento formaPagamento) {
        this(
                formaPagamento.getValorPagamento(),
                formaPagamento.getMetodoPagamento(),
                formaPagamento.getBandeiraCartao(),
                formaPagamento.getQuantidadeParcelas()
        );
    }

}