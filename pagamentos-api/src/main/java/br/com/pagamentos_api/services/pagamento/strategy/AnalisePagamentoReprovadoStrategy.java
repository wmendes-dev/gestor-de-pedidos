package br.com.pagamentos_api.services.pagamento.strategy;

import br.com.pagamentos_api.dtos.gateway.PagamentoResponse;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.enums.SituacaoAnalisePagamentoEnum;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;
import br.com.pagamentos_api.services.pagamento.AnalisePagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoReprovadoStrategy implements IAnalisePagamentoConclusaoStrategy {

    private final AnalisePagamentoService analisePagamentoService;

    @Override
    @Transactional
    public void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse) {
        analisePagamento.setSituacao(SituacaoAnalisePagamentoEnum.REPROVADA);
        this.analisePagamentoService.atualizarAnalisePagamento(analisePagamento);
    }

    @Override
    public SituacaoPagamentoEnum getSituacaoPagamento() {
        return SituacaoPagamentoEnum.REPROVADO;
    }

}
