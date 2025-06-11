package br.com.pedidos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoContatoEnum {

    TELEFONE("Telefone"),
    CELULAR("Celular"),
    EMAIL("E-mail");

    private final String descricao;

}