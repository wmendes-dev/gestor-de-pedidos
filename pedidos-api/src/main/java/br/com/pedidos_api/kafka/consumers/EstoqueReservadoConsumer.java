package br.com.pedidos_api.kafka.consumers;

import br.com.pedidos_api.dtos.event.EstoqueReservadoEvent;
import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.exceptions.RegraNegocioException;
import br.com.pedidos_api.services.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueReservadoConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstoqueReservadoConsumer.class);

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "estoque-reservado",
            groupId = "grupo-pedidos",
            containerFactory = "estoqueReservadoKafkaListenerContainerFactory"
    )
    public void consumirEventoEstoqueReservado(EstoqueReservadoEvent estoqueReservadoEvent) {
        try {
            this.pedidoService.atualizarSituacaoPedidoPorEventoAtualizacao(estoqueReservadoEvent.idPedido(), EventoAtualizacaoSituacaoPedidoEnum.ESTOQUE_RESERVADO);
        } catch (RegraNegocioException e) {
            LOGGER.warn("Falha ao confirmar pedido {}: {}", estoqueReservadoEvent.idPedido(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao processar evento de estoque reservado para pedido {}: {}", estoqueReservadoEvent.idPedido(), e.getMessage());
        }
    }

}