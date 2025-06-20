package br.com.pagamentos_api.kafka.consumer;

import br.com.pagamentos_api.service.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoCriadaDLTConsumer {

    private final PagamentoService pagamentoService;

    @KafkaListener(
            topics = "analise-pagamento-criada-dlt",
            groupId = "grupo-pagamentos-dlt",
            containerFactory = "analisePagamentoCriadaDLTKafkaListenerContainerFactory"
    )
    public void consumirDLT(Long idAnalisePagamento) {
        this.pagamentoService.reprovarAnalisePagamento(idAnalisePagamento);
    }

}