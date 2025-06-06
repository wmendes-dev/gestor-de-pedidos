package br.com.pedidos_api.mappers;

import br.com.pedidos_api.dtos.request.PedidoRequest;
import br.com.pedidos_api.dtos.request.ProdutoPedidoRequest;
import br.com.pedidos_api.dtos.request.ResumoUsuarioRequest;
import br.com.pedidos_api.dtos.request.ResumoProdutoRequest;
import br.com.pedidos_api.dtos.response.PedidoResponse;
import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.entities.ProdutoPedido;
import br.com.pedidos_api.entities.ResumoUsuario;
import br.com.pedidos_api.entities.ResumoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PedidoMapper {

    public abstract PedidoResponse converterParaPedidoResponse(Pedido pedido);

    @Mapping(target = "usuario", expression = "java(converterParaResumoUsuario(pedidoRequest.usuario()))")
    @Mapping(target = "produtosPedido", expression = "java(converterParaProdutoPedidoList(pedido, pedidoRequest.produtosPedido()))")
    public abstract Pedido converterParaPedido(PedidoRequest pedidoRequest);

    public void converterParaPedido(Pedido pedido, PedidoRequest pedidoRequest) {
        if (pedidoRequest == null) {
            return;
        }

        pedido.setValorSubTotal(pedidoRequest.valorSubTotal());
        pedido.setValorDesconto(pedidoRequest.valorDesconto());
        pedido.setValorTotal(pedidoRequest.valorTotal());

        pedido.setUsuario(converterParaResumoUsuario(pedidoRequest.usuario()));

        List<ProdutoPedido> produtoPedidoList = converterParaProdutoPedidoList(pedido, pedidoRequest.produtosPedido());
        if (pedido.getProdutosPedido() != null) {
            pedido.getProdutosPedido().clear();
            if (produtoPedidoList != null) {
                pedido.getProdutosPedido().addAll(produtoPedidoList);
            }
        } else {
            if (produtoPedidoList != null) {
                pedido.setProdutosPedido(produtoPedidoList);
            }
        }
    }

    public ResumoUsuario converterParaResumoUsuario(ResumoUsuarioRequest resumoUsuarioRequest) {
        return new ResumoUsuario(resumoUsuarioRequest);
    }

    public List<ProdutoPedido> converterParaProdutoPedidoList(Pedido pedido, List<ProdutoPedidoRequest> produtoPedidoRequestList) {
        if (produtoPedidoRequestList == null) {
            return null;
        }

        List<ProdutoPedido> produtoPedidoList = new ArrayList<>();
        for (ProdutoPedidoRequest produtoPedidoRequest : produtoPedidoRequestList) {
            ProdutoPedido produtoPedido = converterParaProdutoPedido(produtoPedidoRequest);
            produtoPedido.setPedido(pedido);
            produtoPedidoList.add(produtoPedido);
        }

        return produtoPedidoList;
    }

    @Mapping(target = "produto", expression = "java(converterParaResumoProduto(produtoPedidoRequest.produto()))")
    public abstract ProdutoPedido converterParaProdutoPedido(ProdutoPedidoRequest produtoPedidoRequest);

    public ResumoProduto converterParaResumoProduto(ResumoProdutoRequest resumoProdutoRequest) {
        return new ResumoProduto(resumoProdutoRequest);
    }

}