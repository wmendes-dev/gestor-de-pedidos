package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoEventoEnum {

    ESTOQUE_RESERVADO("estoque-reservado", "ReservaEstoque", Long.class);

    private final String topico;
    private final String tipoAgregado;
    private final Class<?> classePayload;

}