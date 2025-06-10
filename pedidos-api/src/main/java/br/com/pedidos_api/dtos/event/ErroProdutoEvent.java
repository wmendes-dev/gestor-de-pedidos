package br.com.pedidos_api.dtos.event;

public record ErroProdutoEvent(
        Long idProduto,
        String nomeProduto,
        String motivo
) {
}