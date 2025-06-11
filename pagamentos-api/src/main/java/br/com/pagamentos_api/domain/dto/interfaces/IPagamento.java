package br.com.pagamentos_api.domain.dto.interfaces;

import br.com.pagamentos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.domain.enums.MetodoPagamentoEnum;

import java.math.BigDecimal;

public interface IPagamento {

    Long idPedido();

    BigDecimal valorPagamento();

    MetodoPagamentoEnum metodoPagamento();

    BandeiraCartaoEnum bandeiraCartao();

    Integer quantidadeParcelas();

}
