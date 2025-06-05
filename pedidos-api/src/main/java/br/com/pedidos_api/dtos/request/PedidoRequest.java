package br.com.pedidos_api.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest(
        @NotNull(message = "Valor subtotal é obrigatório")
        BigDecimal valorSubTotal,
        BigDecimal valorDesconto,
        @NotNull(message = "Valor total é obrigatório")
        BigDecimal valorTotal,
        @NotNull(message = "Cliente é obrigatório")
        ResumoClienteRequest cliente,
        @NotNull(message = "Produtos são obrigatórios")
        List<ProdutoPedidoRequest> produtosPedido
) {
}
