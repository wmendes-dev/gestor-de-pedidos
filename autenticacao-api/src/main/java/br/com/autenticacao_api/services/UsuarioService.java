package br.com.autenticacao_api.services;

import br.com.autenticacao_api.repositories.UsuarioRepository;
import br.com.autenticacao_api.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal == null) {
            return null;
        }

        if (principal instanceof Usuario usuario) {
            return usuario;
        }

        throw new IllegalStateException("Usuário logado não conhecido");
    }

}