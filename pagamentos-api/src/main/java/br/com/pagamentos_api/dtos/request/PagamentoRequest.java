package br.com.pagamentos_api.dtos.request;

import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record PagamentoRequest(
        Long idPedido,
        BigDecimal valor,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}
