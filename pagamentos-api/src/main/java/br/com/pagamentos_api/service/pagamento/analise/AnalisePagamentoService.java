package br.com.pagamentos_api.service.pagamento.analise;

import br.com.pagamentos_api.domain.dto.interfaces.IPagamento;
import br.com.pagamentos_api.domain.entity.AnalisePagamento;
import br.com.pagamentos_api.domain.entity.FormaPagamento;
import br.com.pagamentos_api.exception.EntidadeNaoEncontradaException;
import br.com.pagamentos_api.repository.AnalisePagamentoRepository;
import br.com.pagamentos_api.repository.FormaPagamentoRepository;
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
    public AnalisePagamento criarAnalisePagamento(IPagamento iPagamento) {
        AnalisePagamento analisePagamento = new AnalisePagamento();
        analisePagamento.setValor(iPagamento.valorPagamento());
        analisePagamento.setIdPedido(iPagamento.idPedido());
        FormaPagamento formaPagamento = criarFormaPagamento(iPagamento);
        analisePagamento.setFormaPagamento(formaPagamento);
        return this.analisePagamentoRepository.save(analisePagamento);
    }

    private FormaPagamento criarFormaPagamento(IPagamento iPagamento) {
        return this.formaPagamentoRepository.save(new FormaPagamento(iPagamento));
    }

    @Transactional
    public void atualizarAnalisePagamento(AnalisePagamento analisePagamento) {
        analisePagamento.setDataAtualizacao(LocalDateTime.now());
        this.analisePagamentoRepository.save(analisePagamento);
    }

    public AnalisePagamento obterAnalisePagamentoPorId(Long idAnalisePagamento) {
        return this.analisePagamentoRepository.findById(idAnalisePagamento)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Análise de pagamento não encontrada", idAnalisePagamento));
    }

}
