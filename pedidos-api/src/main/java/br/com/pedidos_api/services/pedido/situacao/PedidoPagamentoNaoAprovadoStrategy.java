package br.com.pedidos_api.services.pedido.situacao;

import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.enums.SituacaoPedidoEnum;
import br.com.pedidos_api.exceptions.RegraNegocioException;
import br.com.pedidos_api.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoPagamentoNaoAprovadoStrategy implements IPedidoSituacaoStrategy {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public void atualizarSituacaoPedido(Pedido pedido) {
        if (!pedido.getSituacao().equals(SituacaoPedidoEnum.CRIADO)
                && !pedido.getSituacao().equals(SituacaoPedidoEnum.AGUARDANDO_PAGAMENTO)) {
            throw new RegraNegocioException("Não é possível atualizar a situação do pedido para '%s'"
                    .formatted(SituacaoPedidoEnum.PAGAMENTO_REPROVADO.getDescricao()));
        }

        this.pedidoRepository.atualizarSituacaoPedido(pedido.getIdPedido(), SituacaoPedidoEnum.PAGAMENTO_REPROVADO);
    }

    @Override
    public EventoAtualizacaoSituacaoPedidoEnum getEventoAtualizacaoSituacaoPedido() {
        return EventoAtualizacaoSituacaoPedidoEnum.PAGAMENTO_REPROVADO;
    }

}