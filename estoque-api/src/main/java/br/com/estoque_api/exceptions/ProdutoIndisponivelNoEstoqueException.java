package br.com.estoque_api.exceptions;

import java.math.BigDecimal;

public class ProdutoIndisponivelNoEstoqueException extends NegocioException {

    public ProdutoIndisponivelNoEstoqueException(String nomeProduto, Long idProduto, BigDecimal quantidadeSolicitada, BigDecimal quantidadeDisponivel) {
        super("Produto '%s' indisponível no estoque. [id: %d, qtde. solicitada: %s, qtde. disponível: %s]"
                .formatted(nomeProduto, idProduto, quantidadeSolicitada.toPlainString(), quantidadeDisponivel.toPlainString()));
    }

}