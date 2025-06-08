package br.com.estoque_api.kafka.producers;

import br.com.estoque_api.dtos.event.ProdutoNaoEncontradoEvent;
import br.com.estoque_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProdutoNaoEncontradoProducer implements IEventoProducer<ProdutoNaoEncontradoEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(ProdutoNaoEncontradoEvent produtoNaoEncontradoEvent) {
        return this.kafkaTemplate.send(TipoEventoEnum.PRODUTO_NAO_ENCONTRADO.getTopico(), produtoNaoEncontradoEvent);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.PRODUTO_NAO_ENCONTRADO;
    }

}
