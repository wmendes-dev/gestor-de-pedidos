package br.com.pedidos_api.kafka.producers;


import br.com.pedidos_api.enums.TipoEventoEnum;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface IEventoProducer<T> {

    CompletableFuture<SendResult<String, Object>> publicarEvento(T evento);

    TipoEventoEnum getTipoEvento();

}