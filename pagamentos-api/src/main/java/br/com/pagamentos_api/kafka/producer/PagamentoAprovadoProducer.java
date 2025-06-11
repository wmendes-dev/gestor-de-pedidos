package br.com.pagamentos_api.kafka.producer;

import br.com.pagamentos_api.domain.enums.TipoEventoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PagamentoAprovadoProducer implements IEventoProducer<Long> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> publicarEvento(Long idPedido) {
        return this.kafkaTemplate.send(TipoEventoEnum.PAGAMENTO_APROVADO.getTopico(), idPedido);
    }

    @Override
    public TipoEventoEnum getTipoEvento() {
        return TipoEventoEnum.PAGAMENTO_APROVADO;
    }

}
