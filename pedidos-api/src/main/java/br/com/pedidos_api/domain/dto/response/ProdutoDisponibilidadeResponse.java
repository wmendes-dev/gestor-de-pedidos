package br.com.pedidos_api.domain.dto.response;

public record ProdutoDisponibilidadeResponse(
        Long idProduto,
        Boolean disponivel,
        String mensagem
) {
}