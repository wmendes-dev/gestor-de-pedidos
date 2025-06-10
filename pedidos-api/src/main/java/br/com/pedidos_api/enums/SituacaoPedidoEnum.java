package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SituacaoPedidoEnum {

    CRIADO("Criado"),
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
    CONFIRMADO("Confirmado"),
    ERRO_RESERVA_ESTOQUE("Erro ao reservar estoque"),
    PAGAMENTO_REPROVADO("Pagamento não aprovado"),
    CANCELADO("Cancelado");

    @Getter
    private final String descricao;

}