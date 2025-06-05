package br.com.pagamentos_api.controllers;

import br.com.pagamentos_api.dtos.request.PagamentoRequest;
import br.com.pagamentos_api.services.pagamento.PagamentoService;
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
    public void criarProduto(@RequestBody @Valid PagamentoRequest pagamentoRequest) {
        this.pagamentoService.iniciarAnalisePagamento(pagamentoRequest);
    }

}
