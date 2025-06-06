package br.com.pedidos_api.services;

import br.com.pedidos_api.dtos.event.PedidoCriadoEvent;
import br.com.pedidos_api.dtos.request.PedidoRequest;
import br.com.pedidos_api.dtos.request.PedidoRequestParams;
import br.com.pedidos_api.dtos.response.PedidoPesquisaResponse;
import br.com.pedidos_api.dtos.response.PedidoResponse;
import br.com.pedidos_api.entities.EventoOutbox;
import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.enums.SituacaoEventoOutboxEnum;
import br.com.pedidos_api.enums.SituacaoPedidoEnum;
import br.com.pedidos_api.enums.TipoEventoEnum;
import br.com.pedidos_api.exceptions.EntidadeNaoEncontradaException;
import br.com.pedidos_api.exceptions.RegraNegocioException;
import br.com.pedidos_api.mappers.PedidoMapper;
import br.com.pedidos_api.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PedidoMapper pedidoMapper;

    private final EventoOutboxService eventoOutboxService;

    public Page<PedidoPesquisaResponse> pesquisarPedidos(PedidoRequestParams pedidoRequestParams, Pageable pageable) {
        return this.pedidoRepository.findAll(pedidoRequestParams, pageable);
    }

    public PedidoResponse obterPedido(Long idPedido) {
        Pedido pedido = obterPedidoPorId(idPedido);
        return this.pedidoMapper.converterParaPedidoResponse(pedido);
    }

    @Transactional
    public PedidoResponse criarPedido(PedidoRequest pedidoRequest) {
        Pedido pedido = this.pedidoMapper.converterParaPedido(pedidoRequest);
        pedido = this.pedidoRepository.save(pedido);
        PedidoCriadoEvent pedidoCriadoEvent = new PedidoCriadoEvent(pedido);
        this.eventoOutboxService.criarEvento(pedidoCriadoEvent, TipoEventoEnum.PEDIDO_CRIADO, pedido.getIdPedido().toString());
        return this.pedidoMapper.converterParaPedidoResponse(pedido);
    }

    @Transactional
    public void confirmarPedido(Long idPedido) {
        Pedido pedido = obterPedidoPorId(idPedido);

        if (!pedido.getSituacao().equals(SituacaoPedidoEnum.CRIADO)) {
            throw new RegraNegocioException("Não é possível confirmar o pedido, pois sua situação atual é diferente de 'CRIADO'");
        }

        atualizarSituacaoPedido(pedido, SituacaoPedidoEnum.CONFIRMADO);
    }

    @Transactional
    public void atualizarSituacaoPedido(Pedido pedido, SituacaoPedidoEnum situacaoPedidoEnum) {
        this.pedidoRepository.atualizarSituacaoPedido(pedido.getIdPedido(), situacaoPedidoEnum);
    }

    public Pedido obterPedidoPorId(Long idPedido) {
        return this.pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado", idPedido));
    }

}
