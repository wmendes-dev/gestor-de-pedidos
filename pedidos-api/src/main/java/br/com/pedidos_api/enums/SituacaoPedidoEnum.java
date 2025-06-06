package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoPedidoEnum {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado");

    private final String descricao;

}