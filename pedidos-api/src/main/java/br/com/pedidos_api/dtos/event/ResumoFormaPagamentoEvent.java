package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.ResumoFormaPagamento;
import br.com.pedidos_api.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.enums.MetodoPagamentoEnum;

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