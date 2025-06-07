package br.com.estoque_api.kafka.producers;

import br.com.estoque_api.dtos.event.EstoqueReservadoEvent;
import br.com.estoque_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EstoqueReservadoProducer implements IEventoProducer<EstoqueReservadoEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(EstoqueReservadoEvent estoqueReservadoEvent) {
        return this.kafkaTemplate.send(TipoEventoEnum.ESTOQUE_RESERVADO.getTopico(), estoqueReservadoEvent);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.ESTOQUE_RESERVADO;
    }

}
