package br.com.estoque_api.kafka.producers;

import br.com.estoque_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EstoqueReservadoProducer implements IEventoProducer<Long> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(Long idReservaEstoque) {
        return this.kafkaTemplate.send(TipoEventoEnum.ESTOQUE_RESERVADO.getTopico(), idReservaEstoque);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.ESTOQUE_RESERVADO;
    }

}
