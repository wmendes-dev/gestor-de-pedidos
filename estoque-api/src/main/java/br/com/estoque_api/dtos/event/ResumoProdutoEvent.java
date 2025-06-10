package br.com.estoque_api.dtos.event;

public record ResumoProdutoEvent(
        Long idProduto,
        String nome
) {
}