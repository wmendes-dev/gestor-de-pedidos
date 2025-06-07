package br.com.pagamentos_api.dtos.interfaces;

import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public interface IPagamento {

    Long idPedido();

    BigDecimal valorPagamento();

    MetodoPagamentoEnum metodoPagamento();

    BandeiraCartaoEnum bandeiraCartao();

    Integer quantidadeParcelas();

}
