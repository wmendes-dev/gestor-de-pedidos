package br.com.estoque_api.domain.dto.event;

public record ResumoProdutoEvent(
        Long idProduto,
        String nome
) {
}