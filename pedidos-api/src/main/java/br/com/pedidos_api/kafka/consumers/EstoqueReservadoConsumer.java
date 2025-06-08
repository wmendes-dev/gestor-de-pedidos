package br.com.pedidos_api.kafka.consumers;

import br.com.pedidos_api.dtos.event.EstoqueReservadoEvent;
import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.services.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueReservadoConsumer {

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "estoque-reservado",
            groupId = "grupo-pedidos",
            containerFactory = "estoqueReservadoKafkaListenerContainerFactory"
    )
    public void consumirEventoEstoqueReservado(EstoqueReservadoEvent estoqueReservadoEvent) {
        this.pedidoService.atualizarSituacaoPedidoPorEventoAtualizacao(
                estoqueReservadoEvent.idPedido(), EventoAtualizacaoSituacaoPedidoEnum.ESTOQUE_RESERVADO);
    }

}