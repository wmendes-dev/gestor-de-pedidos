package br.com.pedidos_api.domain.dto.event;

import br.com.pedidos_api.domain.entity.ProdutoPedido;

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
