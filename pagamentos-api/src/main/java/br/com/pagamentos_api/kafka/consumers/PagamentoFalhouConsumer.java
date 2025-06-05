package br.com.pagamentos_api.kafka.consumers;

import br.com.pagamentos_api.services.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoFalhouConsumer {

    private final PagamentoService pagamentoService;

    @KafkaListener(
            topics = "pagamento-falhou",
            groupId = "grupo-pagamentos",
            containerFactory = "pagamentoFalhouKafkaListenerContainerFactory"
    )
    public void consumirEventoPagamentoFalhou(Long idAnalisePagamento) {
        this.pagamentoService.reprocessarAnalisePagamento(idAnalisePagamento);
    }

}