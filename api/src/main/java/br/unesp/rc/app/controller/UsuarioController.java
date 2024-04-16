package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Mostra usuario por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }
    
    // Mostra todos usuarios
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        List<Usuario> l = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    // Cria novo usuario
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    // Atualiza usuario
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioSalva = usuarioRepository.findById(id).get();
            usuario.setId(id);
            usuarioSalva = usuarioRepository.save(usuario);
            return new ResponseEntity<>(usuarioSalva, HttpStatus.OK);
        
        } catch(Exception e) {
            return new ResponseEntity("Failed request: user " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }
    
    // Deleta usuario
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity deleteUsuario(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            usuarioRepository.delete(usuario);

            return new ResponseEntity("User " + id + " was deleted", HttpStatus.OK);

        } catch(Exception e) { 
            return new ResponseEntity("Failed request: user " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }
}
