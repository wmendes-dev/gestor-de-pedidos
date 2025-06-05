package br.com.autenticacao_api.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EntidadeNaoEncontradaException(String mensagem, Long id) {
        super(mensagem + " por id = %s".formatted(id));
    }

}