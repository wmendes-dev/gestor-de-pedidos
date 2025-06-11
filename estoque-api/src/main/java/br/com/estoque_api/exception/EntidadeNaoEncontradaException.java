package br.com.estoque_api.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String mensagem, Long id) {
        super(mensagem + " [id: %s]".formatted(id));
    }

}