package br.com.pedidos_api.domain.dto.event;

public record ErroProdutoEvent(
        Long idProduto,
        String nomeProduto,
        String motivo
) {
}