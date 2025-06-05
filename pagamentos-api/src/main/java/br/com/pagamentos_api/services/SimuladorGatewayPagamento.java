package br.com.pagamentos_api.services;

import br.com.pagamentos_api.dtos.gateway.PagamentoResponse;
import br.com.pagamentos_api.entities.AnalisePagamento;
import br.com.pagamentos_api.enums.BandeiraCartaoEnum;
import br.com.pagamentos_api.enums.MetodoPagamentoEnum;
import br.com.pagamentos_api.enums.SituacaoPagamentoEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Service
public class SimuladorGatewayPagamento {

    private final Random random = new Random();

    public void processarAsync(AnalisePagamento analisePagamento, Consumer<PagamentoResponse> callback) {
        CompletableFuture.runAsync(() -> {
            PagamentoResponse pagamentoResponse = null;
            BigDecimal valor = analisePagamento.getValor();
            MetodoPagamentoEnum metodoPagamento = analisePagamento.getFormaPagamento().getMetodoPagamento();
            BandeiraCartaoEnum bandeiraCartao = analisePagamento.getFormaPagamento().getBandeiraCartao();

            try {
                Thread.sleep(1000 + this.random.nextInt(2000));
                String idPagamento = "pgto-" + UUID.randomUUID();
                SituacaoPagamentoEnum situacao;
                int porcentagemSituacao = this.random.nextInt(100);
                if (porcentagemSituacao < 60) situacao = SituacaoPagamentoEnum.APROVADO;
                else if (porcentagemSituacao < 90) situacao = SituacaoPagamentoEnum.REPROVADO;
                else throw new InterruptedException();

                pagamentoResponse = new PagamentoResponse(
                        idPagamento,
                        situacao,
                        valor,
                        LocalDateTime.now(),
                        metodoPagamento,
                        bandeiraCartao
                );
            } catch (InterruptedException e) {
                pagamentoResponse = new PagamentoResponse(
                        "pgto-error",
                        SituacaoPagamentoEnum.FALHA,
                        valor,
                        LocalDateTime.now(),
                        metodoPagamento,
                        bandeiraCartao
                );
            } finally {
                callback.accept(pagamentoResponse);
            }
        });
    }

}
