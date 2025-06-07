package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.enums.BandeiraCartaoEnum;
import br.com.pedidos_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record EstoqueReservadoEvent(
        Long idPedido,
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {
}