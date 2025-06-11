package br.com.estoque_api.mapper;

import br.com.estoque_api.domain.dto.request.ProdutoRequest;
import br.com.estoque_api.domain.dto.response.ProdutoResponse;
import br.com.estoque_api.domain.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse converterParaProdutoResponse(Produto produto);

    Produto converterParaProduto(ProdutoRequest produtoRequest);

    void converterParaProduto(@MappingTarget Produto produto, ProdutoRequest produtoRequest);

}