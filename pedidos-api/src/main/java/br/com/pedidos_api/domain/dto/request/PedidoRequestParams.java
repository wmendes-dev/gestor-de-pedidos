package br.com.pedidos_api.domain.dto.request;

import java.time.LocalDate;

public record PedidoRequestParams(
        LocalDate dataInicio,
        LocalDate dataFim,
        String usuario
) {
}
