package br.com.estoque_api.controllers;

import br.com.estoque_api.dtos.request.ProdutoRequest;
import br.com.estoque_api.dtos.response.ProdutoResponse;
import br.com.estoque_api.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> obterProduto(@PathVariable Long idProduto) {
        ProdutoResponse produtoResponse = this.produtoService.obterProduto(idProduto);
        return ResponseEntity.ok(produtoResponse);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = this.produtoService.criarProduto(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long idProduto, @RequestBody @Valid ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = this.produtoService.atualizarProduto(idProduto, produtoRequest);
        return ResponseEntity.ok(produtoResponse);
    }

}
