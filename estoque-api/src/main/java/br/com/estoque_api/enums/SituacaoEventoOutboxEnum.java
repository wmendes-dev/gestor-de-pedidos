package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoEventoOutboxEnum {

    PENDENTE("Pendente"),
    PROCESSADO("Processado");

    private final String descricao;

}