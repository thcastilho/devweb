package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.service.UsuarioService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    // Mostra usuario por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    // Mostra todos usuarios
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    } 

    // Atualiza usuario
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.updateUsuarioById(id, usuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
    }
    
    // Deleta usuario
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idUsuario}/like/{idComentario}")
    public ResponseEntity<Void> likeComment(@PathVariable Long idUsuario, @PathVariable Long idComentario) {
        usuarioService.likeComment(idUsuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/dislike/{idComentario}")
    public ResponseEntity<Void> dislikeComment(@PathVariable Long idUsuario, @PathVariable Long idComentario) {
        usuarioService.dislikeComment(idUsuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/remove-like/{idComentario}")
    public ResponseEntity<Void> removeLike(@PathVariable Long idUsuario, @PathVariable Long idComentario) {
        usuarioService.removeLike(idUsuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/remove-dislike/{idComentario}")
    public ResponseEntity<Void> removeDislike(@PathVariable Long idUsuario, @PathVariable Long idComentario) {
        usuarioService.removeDislike(idUsuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
