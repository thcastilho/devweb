package br.unesp.rc.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class IndexController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // @GetMapping(value = "/", produces = "application/json")
    // public ResponseEntity init(@RequestParam(value="nome", required = false) String nome, 
    //                             @RequestParam(value = "login" , defaultValue = "sem valor") String login) {
    //     return new ResponseEntity("Ola mundo! " + nome + " login igual a: " + login, HttpStatus.OK);
    // }

    // @GetMapping("/abner")
    // public ResponseEntity<Usuario> abner() {
    //     Usuario user = new Usuario();
    //     user.setUserName("otavio");
    //     user.setEmail("otavio@email.com");
    //     user.setPassword("123456");
    //     return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    // }

    // @GetMapping(value="/jason", produces="application/json")
    // public ResponseEntity init3() {
    //     Usuario user1 = new Usuario();
    //     Usuario user2 = new Usuario();

    //     user1.setUserName("otavio");
    //     user1.setEmail("otavio@email.com");
    //     user1.setPassword("123456");

    //     user2.setUserName("gerson");
    //     user2.setEmail("gerson@email.com");
    //     user2.setPassword("654321");

    //     List<Usuario> list = new ArrayList<Usuario>();
        
    //     list.add(user1);
    //     list.add(user2);

    //     return new ResponseEntity<>(list, HttpStatus.OK);
    // }

    @GetMapping(value="/{id}", produces="application/json")
    /* Para ser RESTful, devemos priorizar PathVariable a RequestParam */
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") Long id) {
        try {
            Usuario user = usuarioRepository.findById(id).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> l = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    
    @GetMapping(value="/{id}/relatorio/{dias}", produces="application/json")
    public ResponseEntity relatorio(@PathVariable(value="id") Long id, @PathVariable(value="dias") int dias){
        return new ResponseEntity("Relatorio do usuario " + id + " para os " + dias + " dias anteriores.", HttpStatus.OK);
    }
    
    @PostMapping(value = "/", consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    // ISSO NAO EH RESTFUL, pq precisa retornar uma resposta no formato json
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deleteUser(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);

        return "ok";
    }
}
