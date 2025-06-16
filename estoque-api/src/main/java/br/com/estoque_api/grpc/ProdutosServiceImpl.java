package br.com.estoque_api.grpc;

import br.com.estoque_api.domain.entity.Produto;
import br.com.estoque_api.exception.EntidadeNaoEncontradaException;
import br.com.estoque_api.exception.NegocioException;
import br.com.estoque_api.exception.ProdutoIndisponivelException;
import br.com.estoque_api.service.EstoqueService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.Map;

@GrpcService
@RequiredArgsConstructor
public class ProdutosServiceImpl extends ProdutosServiceGrpc.ProdutosServiceImplBase {

    private final EstoqueService estoqueService;

    private static final Map<Class<? extends NegocioException>, String> MOTIVO_ERRO_MAP = Map.of(
            ProdutoIndisponivelException.class, "Produto indisponível no momento",
            EntidadeNaoEncontradaException.class, "Produto não encontrado"
    );

    @Override
    public void validarDisponibilidadeProdutos(ProdutosRequest request,
                                               StreamObserver<ProdutosResponse> responseObserver) {

        ProdutosResponse.Builder responseBuilder = ProdutosResponse.newBuilder();

        request.getProdutosList().forEach(produtoRequest ->
                responseBuilder.addProdutos(buildProdutoResponse(produtoRequest))
        );

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private ProdutoResponse buildProdutoResponse(ProdutoRequest produtoRequest) {
        long idProduto = produtoRequest.getIdProduto();
        BigDecimal quantidadeSolicitada = new BigDecimal(produtoRequest.getQuantidade());

        ProdutoResponse.Builder builder = ProdutoResponse.newBuilder().setIdProduto(idProduto);

        try {
            Produto produto = estoqueService.validarDisponibilidadeDoProduto(idProduto, quantidadeSolicitada);
            builder
                    .setIdProduto(produto.getIdProduto())
                    .setDisponivel(true)
                    .setMensagem("Produto disponível");
        } catch (NegocioException e) {
            String motivoErro = MOTIVO_ERRO_MAP.getOrDefault(e.getClass(), "Erro inesperado ao validar disponibilidade do produto");
            builder
                    .setIdProduto(idProduto)
                    .setDisponivel(false)
                    .setMensagem(motivoErro);
        }

        return builder.build();
    }

}
