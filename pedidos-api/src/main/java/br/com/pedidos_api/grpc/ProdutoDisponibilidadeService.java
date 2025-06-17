package br.com.pedidos_api.grpc;

import br.com.estoque_api.grpc.ProdutoRequest;
import br.com.estoque_api.grpc.ProdutosRequest;
import br.com.estoque_api.grpc.ProdutosResponse;
import br.com.estoque_api.grpc.ProdutosServiceGrpc;
import br.com.pedidos_api.domain.dto.request.ProdutoDisponibilidadeRequest;
import br.com.pedidos_api.domain.dto.response.ProdutoDisponibilidadeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.client.ImportGrpcClients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ImportGrpcClients(basePackageClasses = ProdutosServiceGrpc.class)
public class ProdutoDisponibilidadeService {

    private final ProdutosServiceGrpc.ProdutosServiceBlockingStub serviceBlockingStub;

    public List<ProdutoDisponibilidadeResponse> validarDisponibilidadeProdutos(List<ProdutoDisponibilidadeRequest> produtoDisponibilidadeRequestList) {
        ProdutosRequest request = ProdutosRequest.newBuilder()
                .addAllProdutos(produtoDisponibilidadeRequestList.stream()
                        .map(produtoDisponibilidadeRequest -> ProdutoRequest.newBuilder()
                                .setIdProduto(produtoDisponibilidadeRequest.idProduto())
                                .setQuantidade(produtoDisponibilidadeRequest.quantidade().toString())
                                .build())
                        .toList())
                .build();

        ProdutosResponse response = this.serviceBlockingStub.validarDisponibilidadeProdutos(request);

        return response.getProdutosList().stream()
                .map(produtoResponse -> new ProdutoDisponibilidadeResponse(
                        produtoResponse.getIdProduto(),
                        produtoResponse.getDisponivel(),
                        produtoResponse.getMensagem()
                )).toList();
    }

}
