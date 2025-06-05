package br.com.autenticacao_api.mappers;

import br.com.autenticacao_api.dtos.response.UsuarioResponse;
import br.com.autenticacao_api.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse converterParaUsuarioResponse(Usuario usuario);

}