package br.unesp.rc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario updateUsuarioById(Long id, Usuario usuario) {
        Usuario usuarioSalva = usuarioRepository.findById(id).get();
        usuario.setId(id);
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setSenha(encryptedPassword);
        usuarioSalva = usuarioRepository.save(usuario);
        
        return usuarioSalva;
    }
}
