package br.unesp.rc.app.controller;

import br.unesp.rc.app.dto.UpdateUserDTO;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    // Mostra as infos do usuário logado
    @CrossOrigin("http://localhost:3000/")
    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<Usuario> getUsuarioByToken(@RequestHeader (name="Authorization") String token) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    // Mostra todos usuarios
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    } 

    // Atualiza usuario
    @CrossOrigin("http://localhost:3000/")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> updateUsuario(@RequestHeader (name="Authorization") String token, @RequestBody @Valid UpdateUserDTO data) {
        try {
            Usuario usuarioSalvo = usuarioService.updateUsuarioByToken(token, data);
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Deleta usuario
    @CrossOrigin("http://localhost:3000/")
    @DeleteMapping(value = "/", produces = "application/text")
    public ResponseEntity<Void> deleteUsuario(@RequestHeader (name="Authorization") String token) {
        usuarioService.deleteUsuario(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/like/{idComentario}")
    public ResponseEntity<Void> likeComment(@RequestHeader (name="Authorization") String token, @PathVariable Long idComentario) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        usuarioService.likeComment(usuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/dislike/{idComentario}")
    public ResponseEntity<Void> dislikeComment(@RequestHeader (name="Authorization") String token, @PathVariable Long idComentario) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        usuarioService.dislikeComment(usuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/remove-like/{idComentario}")
    public ResponseEntity<Void> removeLike(@RequestHeader (name="Authorization") String token, @PathVariable Long idComentario) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        usuarioService.removeLike(usuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/remove-dislike/{idComentario}")
    public ResponseEntity<Void> removeDislike(@RequestHeader (name="Authorization") String token, @PathVariable Long idComentario) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        usuarioService.removeDislike(usuario, idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
