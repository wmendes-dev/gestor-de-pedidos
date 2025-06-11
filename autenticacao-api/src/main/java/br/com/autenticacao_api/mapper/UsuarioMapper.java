package br.com.autenticacao_api.mapper;

import br.com.autenticacao_api.domain.dto.response.UsuarioResponse;
import br.com.autenticacao_api.domain.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse converterParaUsuarioResponse(Usuario usuario);

}