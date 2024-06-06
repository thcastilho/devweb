package br.unesp.rc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Comentario;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.ComentarioRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TokenService tokenService;

    public Usuario getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return usuario;
    }

    public Usuario getUsuarioByToken(String token) {
        Usuario usuario = usuarioRepository.findByLogin(tokenService.validateToken(token.replace("Bearer ", "")));
        System.out.println(usuario);
        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario updateUsuarioById(Long id, Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        usuario.setId(id);
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setSenha(encryptedPassword);
        usuarioSalvo = usuarioRepository.save(usuario);
        
        return usuarioSalvo;
    }

    public Usuario updateUsuarioByToken(String token, Usuario usuario) {
        System.out.println("entrou aki");
        Usuario usuarioSalvo = usuarioRepository.findByLogin(tokenService.validateToken(token.replace("Bearer ", "")));
        
        usuario.setId(usuarioSalvo.getId());

        if (usuario.getPassword() != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
            usuario.setSenha(encryptedPassword);
        } else {
            usuario.setSenha(usuarioSalvo.getSenha());
        }

        usuarioSalvo = usuarioRepository.save(usuario);
        System.out.println("chegou ao final");
        return usuarioSalvo;
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        usuarioRepository.delete(usuario);
    }

    public void likeComment(Usuario usuario, Long idComentario) {     
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getDislikes().contains(comentario)) {
            removeDislike(usuario, idComentario);
        }
        
        if(!usuario.getLikes().contains(comentario)) {
            usuario.assignLike(comentario);
            int numLikes = comentario.getNumLikes() + 1;
            comentario.setNumLikes(numLikes);
            
            usuarioRepository.save(usuario);
            comentarioRepository.save(comentario);
        } else {
            throw new IllegalArgumentException("Comment already liked");
        }
    }

    public void dislikeComment(Usuario usuario, Long idComentario) {  
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getLikes().contains(comentario)) {
            removeLike(usuario, idComentario);
        }
        
        if(!usuario.getDislikes().contains(comentario)) {
            usuario.assignDislike(comentario);
            int numDislikes = comentario.getNumDislikes() + 1;
            comentario.setNumDislikes(numDislikes);
                
            usuarioRepository.save(usuario);
            comentarioRepository.save(comentario);
        } else {
            throw new IllegalArgumentException("Comment already disliked");
        }
    }

    public void removeLike(Usuario usuario, Long idComentario) {
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getLikes().contains(comentario)) {
            usuario.getLikes().remove(comentario);
            int numLikes = comentario.getNumLikes() - 1;
            comentario.setNumLikes(numLikes);
            
            usuarioRepository.save(usuario);
            comentarioRepository.save(comentario);
        } else {
            throw new IllegalArgumentException("Comment not liked");
        }
    }

    public void removeDislike(Usuario usuario, Long idComentario) {        
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getDislikes().contains(comentario)) {
            usuario.getDislikes().remove(comentario);
            int numDislikes = comentario.getNumDislikes() - 1;
            comentario.setNumDislikes(numDislikes);
            
            usuarioRepository.save(usuario);
            comentarioRepository.save(comentario);
        } else {
            throw new IllegalArgumentException("Comment not disliked");
        }
    }
}
