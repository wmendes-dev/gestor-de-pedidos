package br.com.pedidos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SituacaoEventoOutboxEnum {

    PENDENTE("Pendente"),
    PROCESSADO("Processado");

    private final String descricao;

}