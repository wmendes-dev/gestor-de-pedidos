package br.com.pagamentos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoEventoEnum {

    ANALISE_PAGAMENTO_CRIADA("analise-pagamento-criada", "AnalisePagamento", Long.class),
    PAGAMENTO_APROVADO("pagamento-aprovado", "AnalisePagamento", Long.class),
    PAGAMENTO_REPROVADO("pagamento-reprovado", "AnalisePagamento", Long.class),
    PAGAMENTO_FALHOU("pagamento-falhou", "AnalisePagamento", Long.class);

    private final String topico;
    private final String tipoAgregado;
    private final Class<?> classePayload;

}