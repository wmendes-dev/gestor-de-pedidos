package br.com.pagamentos_api.dtos.event;


import br.com.pagamentos_api.dtos.interfaces.IPagamento;
import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record EstoqueReservadoEvent(
        Long idPedido,
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) implements IPagamento {
}
