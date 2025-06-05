package br.com.pagamentos_api.kafka.consumers;

import br.com.pagamentos_api.services.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoCriadaConsumer {

    private final PagamentoService pagamentoService;

    @KafkaListener(
            topics = "analise-pagamento-criada",
            groupId = "grupo-pagamentos",
            containerFactory = "analisePagamentoCriadaKafkaListenerContainerFactory"
    )
    public void consumirEventoAnalisePagamentoCriada(Long idAnalisePagamento) {
        this.pagamentoService.verificarGatewayPagamento(idAnalisePagamento);
    }

}