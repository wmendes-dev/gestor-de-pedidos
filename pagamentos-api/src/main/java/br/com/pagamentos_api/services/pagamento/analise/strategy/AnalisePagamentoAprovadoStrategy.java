package br.com.pagamentos_api.services.pagamento.analise.strategy;

import br.com.pagamentos_api.dtos.gateway.PagamentoResponse;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.entities.PagamentoAprovado;
import br.com.pagamentos_api.enums.SituacaoAnalisePagamentoEnum;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;
import br.com.pagamentos_api.enums.TipoEventoEnum;
import br.com.pagamentos_api.repositories.PagamentoAprovadoRepository;
import br.com.pagamentos_api.services.EventoOutboxService;
import br.com.pagamentos_api.services.pagamento.analise.AnalisePagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoAprovadoStrategy implements IAnalisePagamentoConclusaoStrategy {

    private final AnalisePagamentoService analisePagamentoService;

    private final PagamentoAprovadoRepository pagamentoAprovadoRepository;

    private final EventoOutboxService eventoOutboxService;

    @Override
    @Transactional
    public void concluirAnalise(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse) {
        analisePagamento.setSituacao(SituacaoAnalisePagamentoEnum.APROVADA);
        this.analisePagamentoService.atualizarAnalisePagamento(analisePagamento);
        criarPagamentoAprovado(analisePagamento, pagamentoResponse);
        this.eventoOutboxService.criarEvento(
                analisePagamento.getIdPedido(),
                TipoEventoEnum.PAGAMENTO_APROVADO,
                analisePagamento.getIdAnalisePagamento().toString());
    }

    private void criarPagamentoAprovado(AnalisePagamento analisePagamento, PagamentoResponse pagamentoResponse) {
        PagamentoAprovado pagamentoAprovado = new PagamentoAprovado();
        pagamentoAprovado.setIdPagamento(pagamentoResponse.idPagamento());
        pagamentoAprovado.setDataAprovacao(pagamentoResponse.dataProcessamento());
        pagamentoAprovado.setAnalisePagamento(analisePagamento);
        this.pagamentoAprovadoRepository.save(pagamentoAprovado);
    }

    @Override
    public SituacaoPagamentoEnum getSituacaoPagamento() {
        return SituacaoPagamentoEnum.APROVADO;
    }

}
