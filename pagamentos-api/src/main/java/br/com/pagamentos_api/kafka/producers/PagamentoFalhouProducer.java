package br.com.pagamentos_api.kafka.producers;

import br.com.pagamentos_api.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PagamentoFalhouProducer implements IEventoProducer<Long> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(Long idAnalisePagamento) {
        return this.kafkaTemplate.send(TipoEventoEnum.PAGAMENTO_FALHOU.getTopico(), idAnalisePagamento);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.PAGAMENTO_FALHOU;
    }

}
