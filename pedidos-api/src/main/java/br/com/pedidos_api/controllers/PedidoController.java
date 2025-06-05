package br.com.pedidos_api.controllers;

import br.com.pedidos_api.dtos.request.PedidoRequest;
import br.com.pedidos_api.dtos.request.PedidoRequestParams;
import br.com.pedidos_api.dtos.response.PedidoPesquisaResponse;
import br.com.pedidos_api.dtos.response.PedidoResponse;
import br.com.pedidos_api.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<PedidoPesquisaResponse>> pesquisarPedidos(@Nullable PedidoRequestParams pedidoRequestParams, @Nullable Pageable pageable) {
        Page<PedidoPesquisaResponse> pedidoPesquisaResponsePage = this.pedidoService.pesquisarPedidos(pedidoRequestParams, pageable);
        return ResponseEntity.ok(pedidoPesquisaResponsePage);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoResponse> obterPedido(@PathVariable Long idPedido) {
        PedidoResponse pedidoResponse = this.pedidoService.obterPedido(idPedido);
        return ResponseEntity.ok(pedidoResponse);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody @Valid PedidoRequest pedidoRequest) {
        PedidoResponse pedidoResponse = this.pedidoService.criarPedido(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponse);
    }

}
