package br.com.pedidos_api.mappers;

import br.com.pedidos_api.dtos.request.*;
import br.com.pedidos_api.dtos.response.PedidoResponse;
import br.com.pedidos_api.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PedidoMapper {

    public abstract PedidoResponse converterParaPedidoResponse(Pedido pedido);

    @Mapping(target = "produtosPedido", expression = "java(converterParaProdutoPedidoList(pedido, pedidoRequest.produtosPedido()))")
    public abstract Pedido converterParaPedido(PedidoRequest pedidoRequest);

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