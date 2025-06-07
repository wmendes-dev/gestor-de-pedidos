package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventoAtualizacaoSituacaoPedidoEnum {

    ESTOQUE_MOVIMENTADO("Estoque movimentado"),
    ESTOQUE_RESERVADO("Estoque reservado"),
    PAGAMENTO_APROVADO("Pagamento aprovado"),
    PAGAMENTO_REPROVADO("Pagamento não aprovado");

    private final String descricao;

}
