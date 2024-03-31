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
    
    //Teste pra ver se a controller funciona
    @GetMapping(value="/")
    public String teste() {
        return "Ok";
    }
    
    //Mostra usuario por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }
    
    //Mostra todas usuarios
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        List<Usuario> l = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    //Cria nova usuario
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    //Atualiza usuario
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalva = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioSalva, HttpStatus.OK);
    }
    
    //Deleta usuario
    // ISSO NAO EH RESTFUL, pq precisa retornar uma usuario no formato json
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deleteUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);

        return "ok";
    }
}
