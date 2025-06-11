package br.com.estoque_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoEnum {

    ATIVO("Ativo"),
    BLOQUEADO("Bloqueado");

    private final String descricao;

}