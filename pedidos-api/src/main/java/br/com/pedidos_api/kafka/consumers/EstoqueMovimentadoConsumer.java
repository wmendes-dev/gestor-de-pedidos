package br.com.pedidos_api.kafka.consumers;

import br.com.pedidos_api.exceptions.RegraNegocioException;
import br.com.pedidos_api.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueMovimentadoConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstoqueMovimentadoConsumer.class);

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "estoque-movimentado",
            groupId = "grupo-pedidos",
            containerFactory = "estoqueMovimentadoKafkaListenerContainerFactory"
    )
    public void consumirEventoEstoqueMovimentado(Long idPedido) {
        try {
            this.pedidoService.confirmarPedido(idPedido);
        } catch (RegraNegocioException e) {
            LOGGER.warn("Falha ao confirmar pedido {}: {}", idPedido, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao processar evento de estoque movimentado para pedido {}", idPedido, e);
        }
    }

}