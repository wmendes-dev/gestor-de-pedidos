package br.com.pedidos_api.dtos.event;

import br.com.pedidos_api.entities.ResumoProduto;

public record ResumoProdutoEvent(
        Long idProduto,
        String nome
) {

    public ResumoProdutoEvent(ResumoProduto produto) {
        this(
                produto.getIdProduto(),
                produto.getNome()
        );
    }

}