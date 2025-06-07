package br.com.estoque_api.enums;

import br.com.estoque_api.dtos.event.EstoqueReservadoEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoEventoEnum {

    ESTOQUE_RESERVADO("estoque-reservado", "ReservaEstoque", EstoqueReservadoEvent.class),
    ESTOQUE_MOVIMENTADO("estoque-movimentado", "MovimentacaoEstoque", Long.class);

    private final String topico;
    private final String tipoAgregado;
    private final Class<?> classePayload;

}