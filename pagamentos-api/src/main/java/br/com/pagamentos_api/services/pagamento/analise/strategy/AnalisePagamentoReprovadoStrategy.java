package br.com.pagamentos_api.services.pagamento.analise.strategy;

import br.com.pagamentos_api.dtos.gateway.PagamentoResponse;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.enums.SituacaoAnalisePagamentoEnum;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;
import br.com.pagamentos_api.enums.TipoEventoEnum;
import br.com.pagamentos_api.services.EventoOutboxService;
import br.com.pagamentos_api.services.pagamento.analise.AnalisePagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoReprovadoStrategy implements IAnalisePagamentoConclusaoStrategy {

    private final AnalisePagamentoService analisePagamentoService;

    private final EventoOutboxService eventoOutboxService;

    @Override
    @Transactional
    public void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse) {
        analisePagamento.setSituacao(SituacaoAnalisePagamentoEnum.REPROVADA);
        this.analisePagamentoService.atualizarAnalisePagamento(analisePagamento);
        this.eventoOutboxService.criarEvento(
                analisePagamento.getIdPedido(),
                TipoEventoEnum.PAGAMENTO_REPROVADO,
                analisePagamento.getIdAnalisePagamento().toString());
    }

    @Override
    public SituacaoPagamentoEnum getSituacaoPagamento() {
        return SituacaoPagamentoEnum.REPROVADO;
    }

}
