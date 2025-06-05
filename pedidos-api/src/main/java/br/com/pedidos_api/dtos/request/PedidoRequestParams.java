package br.com.pedidos_api.dtos.request;

import java.time.LocalDate;

public record PedidoRequestParams(
        LocalDate dataInicio,
        LocalDate dataFim,
        String cliente
) {
}
