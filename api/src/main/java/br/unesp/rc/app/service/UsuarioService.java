package br.unesp.rc.app.service;

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

    public Usuario updateUsuarioById(Long id, Usuario usuario) {
        Usuario usuarioSalva = usuarioRepository.findById(id).get();
        usuario.setId(id);
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setSenha(encryptedPassword);
        usuarioSalva = usuarioRepository.save(usuario);
        
        return usuarioSalva;
    }

    public void likeComment(Long idUsuario, Long idComentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getDislikes().contains(comentario)) {
            removeDislike(idUsuario, idComentario);
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

    public void dislikeComment(Long idUsuario, Long idComentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        Comentario comentario = comentarioRepository.findById(idComentario)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(usuario.getLikes().contains(comentario)) {
            removeLike(idUsuario, idComentario);
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

    public void removeLike(Long idUsuario, Long idComentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
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

    public void removeDislike(Long idUsuario, Long idComentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
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
