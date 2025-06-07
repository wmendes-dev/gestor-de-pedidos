package br.com.pedidos_api.dtos.response;

import br.com.pedidos_api.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record ResumoFormaPagamentoResponse(
        BigDecimal valor,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}