package br.com.pedidos_api.kafka.consumers;

import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.services.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoReprovadoConsumer {

    private final PedidoService pedidoService;

    @KafkaListener(
            topics = "pagamento-reprovado",
            groupId = "grupo-pedidos",
            containerFactory = "pagamentoReprovadoKafkaListenerContainerFactory"
    )
    public void consumirEventoPagamentoReprovado(Long idPedido) {
        this.pedidoService.atualizarSituacaoPedidoPorEventoAtualizacao(
                idPedido, EventoAtualizacaoSituacaoPedidoEnum.PAGAMENTO_REPROVADO);
    }

}