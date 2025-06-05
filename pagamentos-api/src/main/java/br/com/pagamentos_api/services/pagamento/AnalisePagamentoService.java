package br.com.pagamentos_api.services.pagamento;

import br.com.pagamentos_api.dtos.request.PagamentoRequest;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.entities.FormaPagamento;
import br.com.pagamentos_api.enums.SituacaoAnalisePagamentoEnum;
import br.com.pagamentos_api.exceptions.EntidadeNaoEncontradaException;
import br.com.pagamentos_api.repositories.AnalisePagamentoRepository;
import br.com.pagamentos_api.repositories.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnalisePagamentoService {

    private final AnalisePagamentoRepository analisePagamentoRepository;

    private final FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public AnalisePagamento criarAnalisePagamento(PagamentoRequest pagamentoRequest) {
        AnalisePagamento analisePagamento = new AnalisePagamento();
        analisePagamento.setValor(pagamentoRequest.valor());
        analisePagamento.setIdPedido(pagamentoRequest.idPedido());
        FormaPagamento formaPagamento = criarFormaPagamento(pagamentoRequest);
        analisePagamento.setFormaPagamento(formaPagamento);
        return this.analisePagamentoRepository.save(analisePagamento);
    }

    private FormaPagamento criarFormaPagamento(PagamentoRequest pagamentoRequest) {
        return this.formaPagamentoRepository.save(new FormaPagamento(pagamentoRequest));
    }

    @Transactional
    public void atualizarAnalisePagamento(AnalisePagamento analisePagamento) {
        analisePagamento.setDataAtualizacao(LocalDateTime.now());
        this.analisePagamentoRepository.save(analisePagamento);
    }

    @Transactional
    public void cancelarAnalisePagamento(AnalisePagamento analisePagamento) {
        analisePagamento.setSituacao(SituacaoAnalisePagamentoEnum.CANCELADA);
        analisePagamento.setDataAtualizacao(LocalDateTime.now());
        this.analisePagamentoRepository.save(analisePagamento);
    }

    public AnalisePagamento obterAnalisePagamentoPorId(Long idAnalisePagamento) {
        return this.analisePagamentoRepository.findById(idAnalisePagamento)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Análise de pagamento não encontrada", idAnalisePagamento));
    }

}
