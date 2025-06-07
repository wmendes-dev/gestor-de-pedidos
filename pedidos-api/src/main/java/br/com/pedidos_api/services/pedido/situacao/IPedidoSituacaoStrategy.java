package br.com.pedidos_api.services.pedido.situacao;

import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;

public interface IPedidoSituacaoStrategy {

    void atualizarSituacaoPedido(Pedido pedido);

    EventoAtualizacaoSituacaoPedidoEnum getEventoAtualizacaoSituacaoPedido();

}
