package br.com.estoque_api.exception;

import lombok.Getter;

@Getter
public class ProdutoIndisponivelException extends NegocioException {

    public ProdutoIndisponivelException() {
        super("Produto indisponível no estoque");
    }

}