package br.com.pagamentos_api.services.pagamento;

import br.com.pagamentos_api.dtos.interfaces.IPagamento;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.enums.TipoEventoEnum;
import br.com.pagamentos_api.services.EventoOutboxService;
import br.com.pagamentos_api.services.SimuladorGatewayPagamento;
import br.com.pagamentos_api.services.pagamento.analise.AnalisePagamentoService;
import br.com.pagamentos_api.services.pagamento.analise.strategy.AnalisePagamentoConclusaoStrategyFactory;
import br.com.pagamentos_api.services.pagamento.analise.strategy.IAnalisePagamentoConclusaoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final AnalisePagamentoService analisePagamentoService;

    private final EventoOutboxService eventoOutboxService;

    private final SimuladorGatewayPagamento simuladorGatewayPagamento;

    private final AnalisePagamentoConclusaoStrategyFactory analisePagamentoConclusaoStrategyFactory;

    @Transactional
    public void processarAnalisePagamento(IPagamento iPagamento) {
        AnalisePagamento analisePagamento = this.analisePagamentoService.criarAnalisePagamento(iPagamento);
        this.eventoOutboxService.criarEvento(
                analisePagamento.getIdAnalisePagamento(),
                TipoEventoEnum.ANALISE_PAGAMENTO_CRIADA,
                analisePagamento.getIdAnalisePagamento().toString());
    }

    public void reprocessarAnalisePagamento(Long idAnalisePagamento) {
        AnalisePagamento analisePagamento = this.analisePagamentoService.obterAnalisePagamentoPorId(idAnalisePagamento);
        if (analisePagamento.getNumeroTentativa() >= 3) {
            this.analisePagamentoService.cancelarAnalisePagamento(analisePagamento);
            return;
        }
        verificarGatewayPagamento(analisePagamento);
    }

    public void verificarGatewayPagamento(Long idAnalisePagamento) {
        AnalisePagamento analisePagamento = this.analisePagamentoService.obterAnalisePagamentoPorId(idAnalisePagamento);
        verificarGatewayPagamento(analisePagamento);
    }

    private void verificarGatewayPagamento(AnalisePagamento analisePagamento) {
        this.simuladorGatewayPagamento.processarAsync(
                analisePagamento,
                pagamentoResponse -> {
                    IAnalisePagamentoConclusaoStrategy strategy = this.analisePagamentoConclusaoStrategyFactory.getStrategy(pagamentoResponse.situacao());
                    strategy.concluirAnalise(analisePagamento, pagamentoResponse);
                });
    }

}
