package br.com.estoque_api.dtos.event;

import br.com.estoque_api.enums.BandeiraCartaoEnum;
import br.com.estoque_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public record EstoqueReservadoEvent(
        Long idPedido,
        BigDecimal valorPagamento,
        MetodoPagamentoEnum metodoPagamento,
        BandeiraCartaoEnum bandeiraCartao,
        Integer quantidadeParcelas
) {

    public EstoqueReservadoEvent(PedidoCriadoEvent pedidoCriadoEvent) {
        this(
                pedidoCriadoEvent.idPedido(),
                pedidoCriadoEvent.formaPagamento().valorPagamento(),
                pedidoCriadoEvent.formaPagamento().metodoPagamento(),
                pedidoCriadoEvent.formaPagamento().bandeiraCartao(),
                pedidoCriadoEvent.formaPagamento().quantidadeParcelas()
        );
    }

}
