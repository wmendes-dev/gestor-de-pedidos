package br.com.estoque_api.kafka.consumer;

import br.com.estoque_api.domain.dto.event.PedidoCriadoEvent;
import br.com.estoque_api.service.EstoqueService;
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
