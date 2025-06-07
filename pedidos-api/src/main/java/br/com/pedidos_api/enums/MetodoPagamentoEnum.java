package br.com.pedidos_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MetodoPagamentoEnum {

    CARTAO_CREDITO("Cartão de Crédito"),
    PIX("Pix"),
    BOLETO("Boleto");

    private final String descricao;

}