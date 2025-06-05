package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoPedidoEnum {

    CRIADO("Criado"),
    CANCELADO("Cancelado");

    private final String descricao;

}