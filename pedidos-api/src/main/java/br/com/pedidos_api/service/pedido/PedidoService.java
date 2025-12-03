package br.com.pedidos_api.service.pedido;

import br.com.pedidos_api.domain.dto.event.PedidoCriadoEvent;
import br.com.pedidos_api.domain.dto.request.PedidoRequest;
import br.com.pedidos_api.domain.dto.request.PedidoRequestParams;
import br.com.pedidos_api.domain.dto.response.PedidoPesquisaResponse;
import br.com.pedidos_api.domain.dto.response.PedidoResponse;
import br.com.pedidos_api.domain.entity.Pedido;
import br.com.pedidos_api.domain.enums.EventoAtualizacaoSituacaoPedidoEnum;
import br.com.pedidos_api.domain.enums.TipoEventoEnum;
import br.com.pedidos_api.exception.EntidadeNaoEncontradaException;
import br.com.pedidos_api.mapper.PedidoMapper;
import br.com.pedidos_api.repository.PedidoRepository;
import br.com.pedidos_api.service.EventoOutboxService;
import br.com.pedidos_api.service.pedido.situacao.IPedidoSituacaoStrategy;
import br.com.pedidos_api.service.pedido.situacao.PedidoSituacaoStrategyFactory;
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
    private final PedidoSituacaoStrategyFactory pedidoSituacaoStrategyFactory;

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
    public void atualizarSituacaoPedidoPorEventoAtualizacao(Long idPedido, EventoAtualizacaoSituacaoPedidoEnum eventoAtualizacaoSituacaoPedidoEnum) {
        Pedido pedido = obterPedidoPorId(idPedido);
        IPedidoSituacaoStrategy strategy = this.pedidoSituacaoStrategyFactory.getStrategy(eventoAtualizacaoSituacaoPedidoEnum);
        strategy.atualizarSituacaoPedido(pedido);
    }

    public Pedido obterPedidoPorId(Long idPedido) {
        return this.pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado", idPedido));
    }

}
