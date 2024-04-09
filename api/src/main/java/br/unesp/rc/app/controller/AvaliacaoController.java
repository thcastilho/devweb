package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.repository.AvaliacaoRepository;
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
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    
    // Mostra avaliação por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable("id") Long id) {
        try {
            Avaliacao aval = avaliacaoRepository.findById(id).get();
            return new ResponseEntity<>(aval, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }
    
    // Mostra todas avaliações
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Avaliacao>> getAllAvaliacoes() {
        List<Avaliacao> l = (List<Avaliacao>) avaliacaoRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    // Cria nova avaliação
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao aval) {
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(aval);

        return new ResponseEntity<>(avaliacaoSalva, HttpStatus.CREATED);
    }

    // Atualiza avaliação
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Avaliacao> updateAvaliacao(@RequestBody Avaliacao aval) {
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(aval);

        return new ResponseEntity<>(avaliacaoSalva, HttpStatus.OK);
    }
    
    // Deleta avaliação
    // Não é RESTful, porque precisa retornar uma usuario no formato json
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deleteAvaliacao(@PathVariable("id") Long id) {
        avaliacaoRepository.deleteById(id);

        return "ok";
    }
}
