package br.com.pagamentos_api.domain.dto.event;


import br.com.pagamentos_api.domain.dto.interfaces.IPagamento;
import br.com.pagamentos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.domain.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record EstoqueReservadoEvent(
        Long idPedido,
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) implements IPagamento {
}
