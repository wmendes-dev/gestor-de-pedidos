package br.com.pedidos_api.domain.dto.event;

import br.com.pedidos_api.domain.entity.ResumoProduto;

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