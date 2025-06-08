package br.com.estoque_api.enums;

import br.com.estoque_api.dtos.event.EstoqueReservadoEvent;
import br.com.estoque_api.dtos.event.ProdutoIndisponivelEvent;
import br.com.estoque_api.dtos.event.ProdutoNaoEncontradoEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoEventoEnum {

    // SUCESSO
    ESTOQUE_RESERVADO("estoque-reservado", "ReservaEstoque", EstoqueReservadoEvent.class),
    ESTOQUE_MOVIMENTADO("estoque-movimentado", "MovimentacaoEstoque", Long.class),

    // ERRO
    PRODUTO_INDISPONIVEL("erro-reserva-estoque", "Pedido", ProdutoIndisponivelEvent.class),
    PRODUTO_NAO_ENCONTRADO("erro-reserva-estoque", "Pedido", ProdutoNaoEncontradoEvent.class);

    private final String topico;
    private final String tipoAgregado;
    private final Class<?> classePayload;

}