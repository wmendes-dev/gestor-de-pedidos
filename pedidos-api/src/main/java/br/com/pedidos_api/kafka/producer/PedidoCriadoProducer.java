package br.com.pedidos_api.kafka.producer;

import br.com.pedidos_api.domain.dto.event.PedidoCriadoEvent;
import br.com.pedidos_api.domain.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PedidoCriadoProducer implements IEventoProducer<PedidoCriadoEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(PedidoCriadoEvent pedidoCriadoEvent) {
        return this.kafkaTemplate.send(TipoEventoEnum.PEDIDO_CRIADO.getTopico(), pedidoCriadoEvent);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.PEDIDO_CRIADO;
    }

}
