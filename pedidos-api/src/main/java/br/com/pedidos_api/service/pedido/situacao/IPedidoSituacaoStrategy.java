package br.com.pedidos_api.service.pedido.situacao;

import br.com.pedidos_api.domain.entity.Pedido;
import br.com.pedidos_api.domain.enums.EventoAtualizacaoSituacaoPedidoEnum;

public interface IPedidoSituacaoStrategy {

    void atualizarSituacaoPedido(Pedido pedido);

    EventoAtualizacaoSituacaoPedidoEnum getEventoAtualizacaoSituacaoPedido();

}
