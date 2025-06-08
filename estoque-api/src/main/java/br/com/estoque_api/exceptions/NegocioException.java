package br.com.estoque_api.exceptions;

public abstract class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}