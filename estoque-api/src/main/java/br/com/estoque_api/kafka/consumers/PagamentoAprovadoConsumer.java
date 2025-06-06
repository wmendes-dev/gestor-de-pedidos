package br.com.estoque_api.kafka.consumers;

import br.com.estoque_api.entities.MovimentacaoEstoque;
import br.com.estoque_api.enums.TipoEventoEnum;
import br.com.estoque_api.services.EstoqueService;
import br.com.estoque_api.services.EventoOutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PagamentoAprovadoConsumer {

    private final EstoqueService estoqueService;

    private final EventoOutboxService eventoOutboxService;

    @Transactional
    @KafkaListener(
            topics = "pagamento-aprovado",
            groupId = "grupo-estoque",
            containerFactory = "pagamentoAprovadoKafkaListenerContainerFactory"
    )
    public void consumirEventoPagamentoAprovado(Long idPedido) {
        MovimentacaoEstoque movimentacaoEstoque = this.estoqueService.criarMovimentacaoEstoque(idPedido);
        this.eventoOutboxService.criarEvento(
                idPedido,
                TipoEventoEnum.ESTOQUE_MOVIMENTADO,
                movimentacaoEstoque.getIdMovimentacaoEstoque().toString());
    }

}