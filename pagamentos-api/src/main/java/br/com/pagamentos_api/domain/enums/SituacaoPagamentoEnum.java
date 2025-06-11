package br.com.pagamentos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoPagamentoEnum {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    FALHA("Falha");

    private final String descricao;

}
