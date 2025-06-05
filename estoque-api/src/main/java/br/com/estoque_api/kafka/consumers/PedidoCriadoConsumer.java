package br.com.estoque_api.kafka.consumers;

import br.com.estoque_api.dtos.event.PedidoCriadoEvent;
import br.com.estoque_api.services.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoCriadoConsumer {

    private final EstoqueService produtoEstoqueService;

    @KafkaListener(
            topics = "pedido-criado",
            groupId = "grupo-estoque",
            containerFactory = "pedidoCriadoKafkaListenerContainerFactory"
    )
    public void consumirEventoPedidoCriado(PedidoCriadoEvent pedidoCriadoEvent) {
        this.produtoEstoqueService.reservarEstoque(pedidoCriadoEvent);
    }

}
