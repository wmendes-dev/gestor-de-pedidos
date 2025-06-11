package br.com.estoque_api.domain.dto.event;

import java.math.BigDecimal;

public record ProdutoPedidoEvent(
        Long idProdutoPedido,
        BigDecimal quantidade,
        ResumoProdutoEvent produto
) {
}
