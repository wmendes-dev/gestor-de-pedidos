package br.com.pedidos_api.domain.enums;

import br.com.pedidos_api.domain.dto.event.PedidoCriadoEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoEventoEnum {

    PEDIDO_CRIADO("pedido-criado", "Pedido", PedidoCriadoEvent.class);

    private final String topico;
    private final String tipoAgregado;
    private final Class<?> classePayload;

}