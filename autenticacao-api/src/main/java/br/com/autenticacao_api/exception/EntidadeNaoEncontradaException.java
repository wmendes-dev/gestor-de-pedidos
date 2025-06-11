package br.com.autenticacao_api.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EntidadeNaoEncontradaException(String mensagem, Long id) {
        super(mensagem + " por id = %s".formatted(id));
    }

}