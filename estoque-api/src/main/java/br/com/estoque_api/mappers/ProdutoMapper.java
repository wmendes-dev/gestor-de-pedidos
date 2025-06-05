package br.com.estoque_api.mappers;

import br.com.estoque_api.dtos.request.ProdutoRequest;
import br.com.estoque_api.dtos.response.ProdutoResponse;
import br.com.estoque_api.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse converterParaProdutoResponse(Produto produto);

    Produto converterParaProduto(ProdutoRequest produtoRequest);

    void converterParaProduto(@MappingTarget Produto produto, ProdutoRequest produtoRequest);

}