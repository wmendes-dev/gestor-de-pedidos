package br.com.estoque_api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BandeiraCartaoEnum {

    VISA("Visa"),
    MASTERCARD("Mastercard"),
    AMEX("Amex"),
    ELO("Elo");

    private final String descricao;

}
