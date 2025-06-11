package br.com.estoque_api.domain.dto.event;

import java.util.List;

public record ErroReservaEstoqueEvent(
        Long idPedido,
        List<ErroProdutoEvent> erros
) {
}
