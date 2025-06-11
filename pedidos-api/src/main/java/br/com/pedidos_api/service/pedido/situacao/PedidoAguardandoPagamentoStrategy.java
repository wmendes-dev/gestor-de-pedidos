package br.com.pedidos_api.service.pedido.situacao;

import br.com.pedidos_api.domain.entity.Pedido;
import br.com.pedidos_api.domain.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.domain.enums.SituacaoPedidoEnum;
import br.com.pedidos_api.exception.RegraNegocioException;
import br.com.pedidos_api.repository.PedidoRepository;
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