package br.com.pagamentos_api.controller;

import br.com.pagamentos_api.domain.dto.request.PagamentoRequest;
import br.com.pagamentos_api.service.pagamento.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public void processarAnalisePagamento(@RequestBody @Valid PagamentoRequest pagamentoRequest) {
        this.pagamentoService.processarAnalisePagamento(pagamentoRequest);
    }

}
