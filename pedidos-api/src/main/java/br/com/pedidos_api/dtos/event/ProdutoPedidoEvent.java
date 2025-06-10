package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.ProdutoPedido;

import java.math.BigDecimal;

public record ProdutoPedidoEvent(
        Long idProdutoPedido,
        BigDecimal quantidade,
        ResumoProdutoEvent produto
) {

    public ProdutoPedidoEvent(ProdutoPedido produtoPedido) {
        this(
                produtoPedido.getIdProdutoPedido(),
                produtoPedido.getQuantidade(),
                new ResumoProdutoEvent(produtoPedido.getProduto())
        );
    }

}
