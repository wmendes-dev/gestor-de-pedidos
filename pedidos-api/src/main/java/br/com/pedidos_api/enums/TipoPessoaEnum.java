package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPessoaEnum {

    PF("Física"),
    PJ("Jurídica");

    private final String descricao;

}