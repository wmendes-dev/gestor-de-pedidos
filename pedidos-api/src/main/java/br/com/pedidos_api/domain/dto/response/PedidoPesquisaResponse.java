package br.com.pedidos_api.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoPesquisaResponse(
        Long idPedido,
        LocalDate dataEmissao,
        BigDecimal valorTotal,
        String usuario
) {
}