package br.com.pagamentos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoAnalisePagamentoEnum {

    EM_PROCESSAMENTO("Em processamento"),
    APROVADA("Aprovada"),
    REPROVADA("Reprovada");

    private final String descricao;

}
