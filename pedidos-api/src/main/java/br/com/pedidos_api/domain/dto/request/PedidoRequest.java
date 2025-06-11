package br.com.pedidos_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest(
        @NotNull(message = "Valor subtotal é obrigatório")
        BigDecimal valorSubTotal,
        BigDecimal valorDesconto,
        @NotNull(message = "Valor total é obrigatório")
        BigDecimal valorTotal,
        @NotNull(message = "Usuário é obrigatório")
        ResumoUsuarioRequest usuario,
        @NotNull(message = "Forma de pagamento é obrigatório")
        ResumoFormaPagamentoRequest formaPagamento,
        @NotNull(message = "Produtos são obrigatórios")
        List<ProdutoPedidoRequest> produtosPedido
) {
}
