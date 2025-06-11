package br.com.estoque_api.exception;

public abstract class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}