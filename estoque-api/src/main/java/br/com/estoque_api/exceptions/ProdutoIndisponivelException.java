package br.com.estoque_api.exceptions;

import lombok.Getter;

@Getter
public class ProdutoIndisponivelException extends NegocioException {

    public ProdutoIndisponivelException() {
        super("Produto indisponível no estoque");
    }

}