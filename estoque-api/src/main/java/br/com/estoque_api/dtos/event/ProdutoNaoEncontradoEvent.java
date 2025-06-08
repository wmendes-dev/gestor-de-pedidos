package br.com.estoque_api.dtos.event;

public record ProdutoNaoEncontradoEvent(
        Long idPedido,
        String mensagem
) {
}
