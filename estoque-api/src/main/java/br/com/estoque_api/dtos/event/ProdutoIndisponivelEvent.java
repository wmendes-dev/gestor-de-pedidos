package br.com.estoque_api.dtos.event;

public record ProdutoIndisponivelEvent(
        Long idPedido,
        String mensagem
) {
}
