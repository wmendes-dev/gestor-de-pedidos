package br.com.pagamentos_api.services.pagamento.analise.strategy;

import br.com.pagamentos_api.dtos.gateway.PagamentoResponse;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;

public interface IAnalisePagamentoConclusaoStrategy {

    void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse);

    SituacaoPagamentoEnum getSituacaoPagamento();

}