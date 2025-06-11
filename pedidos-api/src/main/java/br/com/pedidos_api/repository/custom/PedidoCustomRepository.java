package br.com.pedidos_api.repository.custom;

import br.com.pedidos_api.domain.dto.request.PedidoRequestParams;
import br.com.pedidos_api.domain.dto.response.PedidoPesquisaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoCustomRepository {

    Page<PedidoPesquisaResponse> findAll(PedidoRequestParams pedidoRequestParams, Pageable pageable);

}