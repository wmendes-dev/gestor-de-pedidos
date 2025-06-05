package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.ProdutoPedido;

import java.math.BigDecimal;

public record ProdutoPedidoEvent(
        Long idProdutoPedido,
        BigDecimal quantidade,
        Long idProduto
) {

    public ProdutoPedidoEvent(ProdutoPedido produtoPedido) {
        this(
                produtoPedido.getIdProdutoPedido(),
                produtoPedido.getQuantidade(),
                produtoPedido.getProduto().getIdProduto()
        );
    }

}
