package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoMovimentacaoEnum {

    ENTRADA("Entrada"),
    SAIDA("Saída");

    private final String descricao;

}
