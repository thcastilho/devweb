package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Genero;
import br.unesp.rc.app.repository.GeneroRepository;
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
@RequestMapping(value = "/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;
    
    // Mostra genero por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Genero> getGeneroById(@PathVariable("id") Long id) {
        try {
            Genero genero = generoRepository.findById(id).get();
            return new ResponseEntity<>(genero, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such genero", HttpStatus.NOT_FOUND);
        }
    }
    
    // Mostra todos generos
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Genero>> getAllGenero() {
        List<Genero> l = (List<Genero>) generoRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    // Cria novo genero
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        Genero generoSalvo = generoRepository.save(genero);

        return new ResponseEntity<>(generoSalvo, HttpStatus.CREATED);
    }

    // Atualiza genero
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Genero> updateGenero(@PathVariable Long id, @RequestBody Genero genero) {
        try {
            Genero generoSalva = generoRepository.findById(id).get();
            genero.setId(id);
            generoSalva = generoRepository.save(genero);
            return new ResponseEntity<>(generoSalva, HttpStatus.OK);
        
        } catch(Exception e) {
            return new ResponseEntity("Failed request: genre " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }
    
    // Deleta genero
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity deleteGenero(@PathVariable("id") Long id) {
        try {
            Genero genero = generoRepository.findById(id).get();
            generoRepository.delete(genero);

            return new ResponseEntity("Genre " + id + " was deleted", HttpStatus.OK);

        } catch(Exception e) { 
            return new ResponseEntity("Failed request: genre " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }
}
