package br.com.pedidos_api.repositories.custom;

import br.com.pedidos_api.dtos.request.PedidoRequestParams;
import br.com.pedidos_api.dtos.response.PedidoPesquisaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoCustomRepository {

    Page<PedidoPesquisaResponse> findAll(PedidoRequestParams pedidoRequestParams, Pageable pageable);

}