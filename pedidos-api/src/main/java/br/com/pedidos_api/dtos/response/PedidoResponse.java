package br.com.pedidos_api.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoResponse(
        Long idPedido,
        LocalDate dataEmissao,
        BigDecimal valorSubTotal,
        BigDecimal valorDesconto,
        BigDecimal valorTotal,
        ResumoClienteResponse cliente,
        List<ProdutoPedidoResponse> produtosPedido
) {
}