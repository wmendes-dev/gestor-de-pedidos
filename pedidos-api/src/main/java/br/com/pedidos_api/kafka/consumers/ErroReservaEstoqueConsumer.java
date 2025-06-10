package br.com.pedidos_api.kafka.consumers;

import br.com.pedidos_api.dtos.event.ErroReservaEstoqueEvent;
import br.com.pedidos_api.dtos.event.EstoqueReservadoEvent;
import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.services.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErroReservaEstoqueConsumer {

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "erro-reserva-estoque",
            groupId = "grupo-pedidos",
            containerFactory = "erroReservaEstoqueKafkaListenerContainerFactory"
    )
    public void consumirEventoErroReservaEstoque(ErroReservaEstoqueEvent erroReservaEstoqueEvent) {
        this.pedidoService.atualizarSituacaoPedidoPorEventoAtualizacao(
                erroReservaEstoqueEvent.idPedido(), EventoAtualizacaoSituacaoPedidoEnum.ERRO_RESERVA_ESTOQUE);
    }

}