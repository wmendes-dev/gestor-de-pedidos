package br.com.estoque_api.dtos.event;

import br.com.estoque_api.enums.BandeiraCartaoEnum;
import br.com.estoque_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record ResumoFormaPagamentoEvent(
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}