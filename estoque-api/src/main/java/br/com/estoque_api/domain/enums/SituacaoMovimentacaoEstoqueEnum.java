package br.com.estoque_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoMovimentacaoEstoqueEnum {

    CRIADA("Criada"),
    CANCELADA("Cancelada");

    private final String descricao;

}
