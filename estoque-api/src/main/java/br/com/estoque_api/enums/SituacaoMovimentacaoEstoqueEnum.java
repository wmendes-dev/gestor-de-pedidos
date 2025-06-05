package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoMovimentacaoEstoqueEnum {

    CRIADA("Criada"),
    CANCELADA("Cancelada");

    private final String descricao;

}
