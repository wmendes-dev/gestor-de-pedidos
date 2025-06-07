package br.com.pagamentos_api.kafka.consumers;

import br.com.pagamentos_api.dtos.event.EstoqueReservadoEvent;
import br.com.pagamentos_api.services.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueReservadoConsumer {

    private final PagamentoService pagamentoService;

    @KafkaListener(
            topics = "estoque-reservado",
            groupId = "grupo-pagamentos",
            containerFactory = "estoqueReservadoKafkaListenerContainerFactory"
    )
    public void consumirEventoEstoqueReservado(EstoqueReservadoEvent estoqueReservadoEvent) {
        this.pagamentoService.processarAnalisePagamento(estoqueReservadoEvent);
    }

}