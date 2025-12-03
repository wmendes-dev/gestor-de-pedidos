package br.com.pedidos_api.controller;

import br.com.pedidos_api.domain.dto.request.ProdutoDisponibilidadeRequest;
import br.com.pedidos_api.domain.dto.response.ProdutoDisponibilidadeResponse;
import br.com.pedidos_api.grpc.ProdutoDisponibilidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final ProdutoDisponibilidadeService produtoDisponibilidadeService;

    @PostMapping("/validar-produtos")
    public ResponseEntity<List<ProdutoDisponibilidadeResponse>> validarDisponibilidadeProdutos(@RequestBody List<ProdutoDisponibilidadeRequest> produtoDisponibilidadeRequestList) {
        List<ProdutoDisponibilidadeResponse> produtoDisponibilidadeResponseList = this.produtoDisponibilidadeService
                .validarDisponibilidadeProdutos(produtoDisponibilidadeRequestList);

        return ResponseEntity.ok(produtoDisponibilidadeResponseList);
    }

}
