package br.com.pedidos_api.dtos.event;

import java.util.List;

public record ErroReservaEstoqueEvent(
        Long idPedido,
        List<ErroProdutoEvent> erros
) {
}