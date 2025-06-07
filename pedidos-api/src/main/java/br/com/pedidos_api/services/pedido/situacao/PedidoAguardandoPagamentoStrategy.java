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
public class PedidoAguardandoPagamentoStrategy implements IPedidoSituacaoStrategy {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public void atualizarSituacaoPedido(Pedido pedido) {
        if (!pedido.getSituacao().equals(SituacaoPedidoEnum.CRIADO)) {
            throw new RegraNegocioException("Não é possível atualizar a situação do pedido para 'Aguardando pagamento'");
        }

        this.pedidoRepository.atualizarSituacaoPedido(pedido.getIdPedido(), SituacaoPedidoEnum.AGUARDANDO_PAGAMENTO);
    }

    @Override
    public EventoAtualizacaoSituacaoPedidoEnum getEventoAtualizacaoSituacaoPedido() {
        return EventoAtualizacaoSituacaoPedidoEnum.ESTOQUE_RESERVADO;
    }

}