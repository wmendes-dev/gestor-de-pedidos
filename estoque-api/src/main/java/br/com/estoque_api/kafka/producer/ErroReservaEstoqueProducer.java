package br.com.estoque_api.kafka.producer;

import br.com.estoque_api.domain.dto.event.ErroReservaEstoqueEvent;
import br.com.estoque_api.domain.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ErroReservaEstoqueProducer implements IEventoProducer<ErroReservaEstoqueEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(ErroReservaEstoqueEvent erroReservaEstoqueEvent) {
        return this.kafkaTemplate.send(TipoEventoEnum.ERRO_RESERVA_ESTOQUE.getTopico(), erroReservaEstoqueEvent);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.ERRO_RESERVA_ESTOQUE;
    }

}
