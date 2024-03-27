package br.unesp.rc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import br.unesp.rc.app.repository.UsuarioRepository;

public class AuthorizationService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
