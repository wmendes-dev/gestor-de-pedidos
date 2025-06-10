package br.com.estoque_api.dtos.event;

public record ErroProdutoEvent(
        Long idProduto,
        String nomeProduto,
        String motivo
) {
}
