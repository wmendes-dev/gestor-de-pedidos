package br.com.pedidos_api.kafka.consumer;

import br.com.pedidos_api.domain.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.service.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueMovimentadoConsumer {

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "estoque-movimentado",
            groupId = "grupo-pedidos",
            containerFactory = "estoqueMovimentadoKafkaListenerContainerFactory"
    )
    public void consumirEventoEstoqueMovimentado(Long idPedido) {
        this.pedidoService.atualizarSituacaoPedidoPorEventoAtualizacao(idPedido, EventoAtualizacaoSituacaoPedidoEnum.ESTOQUE_MOVIMENTADO);
    }

}