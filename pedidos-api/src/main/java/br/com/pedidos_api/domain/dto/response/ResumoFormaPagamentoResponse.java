package br.com.pedidos_api.domain.dto.response;

import br.com.pedidos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.domain.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record ResumoFormaPagamentoResponse(
        BigDecimal valor,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}