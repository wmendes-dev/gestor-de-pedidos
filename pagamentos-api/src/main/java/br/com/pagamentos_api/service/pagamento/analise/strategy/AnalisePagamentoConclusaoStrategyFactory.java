package br.com.pagamentos_api.service.pagamento.analise.strategy;

import br.com.pagamentos_api.domain.enums.SituacaoPagamentoEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AnalisePagamentoConclusaoStrategyFactory {

    private final Map<SituacaoPagamentoEnum, IAnalisePagamentoConclusaoStrategy> strategyMap;

    public AnalisePagamentoConclusaoStrategyFactory(Set<IAnalisePagamentoConclusaoStrategy> strategySet) {
        this.strategyMap = new HashMap<>();
        strategySet.forEach(strategy -> this.strategyMap.put(strategy.getSituacaoPagamento(), strategy));
    }

    public IAnalisePagamentoConclusaoStrategy getStrategy(SituacaoPagamentoEnum situacaoPagamentoEnum) {
        return this.strategyMap.get(situacaoPagamentoEnum);
    }

}
