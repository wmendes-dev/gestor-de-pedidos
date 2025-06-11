package br.com.pagamentos_api.kafka.producer;

import br.com.pagamentos_api.domain.enums.TipoEventoEnum;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface IEventoProducer<T> {

    CompletableFuture<SendResult<String, Object>> publicarEvento(T evento);

    TipoEventoEnum getTipoEvento();

}