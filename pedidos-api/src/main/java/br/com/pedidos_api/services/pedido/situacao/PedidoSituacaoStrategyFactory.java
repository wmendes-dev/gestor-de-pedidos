package br.com.pedidos_api.services.pedido.situacao;

import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PedidoSituacaoStrategyFactory {

    private final Map<EventoAtualizacaoSituacaoPedidoEnum, IPedidoSituacaoStrategy> strategyMap;

    public PedidoSituacaoStrategyFactory(Set<IPedidoSituacaoStrategy> strategySet) {
        this.strategyMap = new HashMap<>();
        strategySet.forEach(strategy -> this.strategyMap.put(strategy.getEventoAtualizacaoSituacaoPedido(), strategy));
    }

    public IPedidoSituacaoStrategy getStrategy(EventoAtualizacaoSituacaoPedidoEnum eventoAtualizacaoSituacaoPedidoEnum) {
        return this.strategyMap.get(eventoAtualizacaoSituacaoPedidoEnum);
    }

}