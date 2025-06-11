package br.com.pedidos_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventoAtualizacaoSituacaoPedidoEnum {

    ESTOQUE_MOVIMENTADO("Estoque movimentado"),
    ESTOQUE_RESERVADO("Estoque reservado"),
    ERRO_RESERVA_ESTOQUE("Erro ao reservar estoque"),
    PAGAMENTO_APROVADO("Pagamento aprovado"),
    PAGAMENTO_REPROVADO("Pagamento não aprovado");

    private final String descricao;

}
