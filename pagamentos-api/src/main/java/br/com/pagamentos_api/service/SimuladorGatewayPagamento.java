package br.com.pagamentos_api.service;

import br.com.pagamentos_api.domain.dto.gateway.PagamentoResponse;
import br.com.pagamentos_api.domain.entity.AnalisePagamento;
import br.com.pagamentos_api.domain.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.domain.enums.MetodoPagamentoEnum;
import br.com.pagamentos_api.domain.enums.SituacaoPagamentoEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class SimuladorGatewayPagamento {

    private final Random random = new Random();

    public PagamentoResponse processarPagamento(AnalisePagamento analisePagamento) {
        BigDecimal valor = analisePagamento.getValor();
        MetodoPagamentoEnum metodoPagamento = analisePagamento.getFormaPagamento().getMetodoPagamento();
        BandeiraCartaoEnum bandeiraCartao = analisePagamento.getFormaPagamento().getBandeiraCartao();

        try {
            Thread.sleep(1000 + this.random.nextInt(2000));
            String idPagamento = "pgto-" + UUID.randomUUID();
            SituacaoPagamentoEnum situacao;
            int porcentagemSituacao = this.random.nextInt(100);
            if (porcentagemSituacao < 60) situacao = SituacaoPagamentoEnum.APROVADO;
            else if (porcentagemSituacao < 80) situacao = SituacaoPagamentoEnum.REPROVADO;
            else throw new InterruptedException();

            return new PagamentoResponse(
                    idPagamento,
                    situacao,
                    valor,
                    LocalDateTime.now(),
                    metodoPagamento,
                    bandeiraCartao
            );
        } catch (InterruptedException e) {
            return new PagamentoResponse(
                    "pgto-error",
                    SituacaoPagamentoEnum.FALHA,
                    valor,
                    LocalDateTime.now(),
                    metodoPagamento,
                    bandeiraCartao
            );
        }
    }

}
