package br.com.pagamentos_api.dtos.gateway;

import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponse(
        String idPagamento,
        SituacaoPagamentoEnum situacao,
        BigDecimal valor,
        LocalDateTime dataProcessamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao
) {
}
