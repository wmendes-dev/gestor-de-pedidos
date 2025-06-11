package br.com.estoque_api.domain.dto.event;

import br.com.estoque_api.domain.enums.BandeiraCartaoEnum;
import br.com.estoque_api.domain.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record ResumoFormaPagamentoEvent(
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}