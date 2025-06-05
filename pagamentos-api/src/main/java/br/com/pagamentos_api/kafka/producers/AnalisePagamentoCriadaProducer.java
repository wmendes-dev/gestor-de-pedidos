package br.com.pagamentos_api.kafka.producers;

import br.com.pagamentos_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoCriadaProducer implements IEventoProducer<Long> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(Long idAnalisePagamento) {
        return this.kafkaTemplate.send(TipoEventoEnum.ANALISE_PAGAMENTO_CRIADA.getTopico(), idAnalisePagamento);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.ANALISE_PAGAMENTO_CRIADA;
    }

}
