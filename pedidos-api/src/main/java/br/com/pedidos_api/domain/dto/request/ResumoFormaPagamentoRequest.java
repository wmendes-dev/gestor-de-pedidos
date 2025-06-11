package br.com.pedidos_api.domain.dto.request;

import br.com.pedidos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.domain.enums.MetodoPagamentoEnum;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ResumoFormaPagamentoRequest(
        @NotNull(message = "Valor do pagamento é obrigatório")
        BigDecimal valorPagamento,
        @NotNull(message = "Método de pagamento é obrigatório")
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        @NotNull(message = "Quantidade de parcelas é obrigatória")
        Integer quantidadeParcelas
) {
}