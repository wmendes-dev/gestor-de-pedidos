package br.com.pagamentos_api.service.pagamento.analise.strategy;

import br.com.pagamentos_api.domain.dto.gateway.PagamentoResponse;
import br.com.pagamentos_api.domain.entity.AnalisePagamento;
import br.com.pagamentos_api.domain.enums.SituacaoPagamentoEnum;

public interface IAnalisePagamentoConclusaoStrategy {

    void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse);

    SituacaoPagamentoEnum getSituacaoPagamento();

}