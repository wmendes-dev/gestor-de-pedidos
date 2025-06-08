package br.com.estoque_api.kafka.consumers;

import br.com.estoque_api.services.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoAprovadoConsumer {

    private final EstoqueService estoqueService;

    @KafkaListener(
            topics = "pagamento-aprovado",
            groupId = "grupo-estoque",
            containerFactory = "pagamentoAprovadoKafkaListenerContainerFactory"
    )
    public void consumirEventoPagamentoAprovado(Long idPedido) {
        this.estoqueService.movimentarEstoque(idPedido);
    }

}