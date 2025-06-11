package br.com.pagamentos_api.kafka.producer;

import br.com.pagamentos_api.domain.enums.TipoEventoEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventoProducerFactory {

    private final Map<TipoEventoEnum, IEventoProducer<?>> producerMap;

    public EventoProducerFactory(Set<IEventoProducer<?>> producerSet) {
        this.producerMap = new HashMap<>();
        producerSet.forEach(producer -> this.producerMap.put(producer.getTipoEvento(), producer));
    }

    public IEventoProducer<?> getProducer(TipoEventoEnum tipoEventoEnum) {
        return this.producerMap.get(tipoEventoEnum);
    }

}
