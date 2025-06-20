package br.com.pagamentos_api.service.pagamento.analise.strategy;

import br.com.pagamentos_api.domain.dto.gateway.PagamentoResponse;
import br.com.pagamentos_api.domain.entity.AnalisePagamento;
import br.com.pagamentos_api.domain.enums.SituacaoPagamentoEnum;
import br.com.pagamentos_api.exception.ErroAoProcessarPagamentoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoFalhouStrategy implements IAnalisePagamentoConclusaoStrategy {

    @Override
    public void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse) {
        throw new ErroAoProcessarPagamentoException();
    }

    @Override
    public SituacaoPagamentoEnum getSituacaoPagamento() {
        return SituacaoPagamentoEnum.FALHA;
    }

}
