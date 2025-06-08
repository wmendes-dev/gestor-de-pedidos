package br.com.estoque_api.kafka.producers;

import br.com.estoque_api.dtos.event.ProdutoIndisponivelEvent;
import br.com.estoque_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProdutoIndisponivelProducer implements IEventoProducer<ProdutoIndisponivelEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(ProdutoIndisponivelEvent produtoIndisponivelEvent) {
        return this.kafkaTemplate.send(TipoEventoEnum.PRODUTO_INDISPONIVEL.getTopico(), produtoIndisponivelEvent);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.PRODUTO_INDISPONIVEL;
    }

}
