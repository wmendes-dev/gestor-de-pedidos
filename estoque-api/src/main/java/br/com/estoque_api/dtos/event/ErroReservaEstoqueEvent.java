package br.com.estoque_api.dtos.event;

import java.util.List;

public record ErroReservaEstoqueEvent(
        Long idPedido,
        List<ErroProdutoEvent> erros
) {
}
