package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Genero;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.service.GeneroService;
import br.unesp.rc.app.service.UsuarioService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private UsuarioService usuarioService;
    
    // // Mostra genero por Id
    // @GetMapping(value="/{id}", produces="application/json")
    // public ResponseEntity<Genero> getGeneroById(@PathVariable("id") Long id) {
    //     Genero genero = generoService.getGeneroById(id);
    //     return new ResponseEntity<>(genero, HttpStatus.OK);
    // }
    
    // Mostra todos generos
    @CrossOrigin("http://localhost:3000/")
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Genero>> getAllGenero() {
        List<Genero> generos = generoService.getAllGeneros();
        return new ResponseEntity<>(generos, HttpStatus.OK);
    } 

    // Cria novo genero
    @CrossOrigin("http://localhost:3000/")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Genero> createGenero(@RequestHeader (name="Authorization") String token, @RequestBody Genero genero) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        Genero generoSalvo = generoService.createGenero(usuario, genero);
        if (generoSalvo == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(generoSalvo, HttpStatus.CREATED);
    }

    // // Atualiza genero
    // @PutMapping(value = "/{id}", produces = "application/json")
    // public ResponseEntity<Genero> updateGenero(@PathVariable Long id, @RequestBody Genero genero) {
    //     Genero generoSalvo = generoService.updateGenero(id, genero);
    //     return new ResponseEntity<>(generoSalvo, HttpStatus.OK);
    // }
    
    // Deleta genero
    @CrossOrigin("http://localhost:3000/")
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity<Void> deleteGenero(@PathVariable("id") Long id) {
        generoService.deleteGenero(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
