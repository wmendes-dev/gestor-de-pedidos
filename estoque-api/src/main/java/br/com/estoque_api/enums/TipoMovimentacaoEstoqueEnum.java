package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoMovimentacaoEstoqueEnum {

    PEDIDO("Pedido"),
    DEVOLUCAO("Devolução"),
    AJUSTE_INVENTARIO("Ajuste inventário");

    private final String descricao;

}
