package br.com.pagamentos_api.domain.dto.gateway;

import br.com.pagamentos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.domain.enums.MetodoPagamentoEnum;
import br.com.pagamentos_api.domain.enums.SituacaoPagamentoEnum;

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
