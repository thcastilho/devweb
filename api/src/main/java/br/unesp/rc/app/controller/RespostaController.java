package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.repository.RespostaRepository;
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
@RequestMapping(value = "/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;
    
    //Teste pra ver se a controller funciona
    @GetMapping(value="/")
    public String teste() {
        return "Ok";
    }
    
    //Mostra resposta por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Resposta> getRespostaById(@PathVariable("id") Long id) {
        try {
            Resposta resposta = respostaRepository.findById(id).get();
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }
    
    //Mostra todas respostas
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Resposta>> getAllResposta() {
        List<Resposta> l = (List<Resposta>) respostaRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    //Cria nova resposta
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Resposta> createResposta(@RequestBody Resposta resposta) {
        Resposta respostaSalvo = respostaRepository.save(resposta);

        return new ResponseEntity<>(respostaSalvo, HttpStatus.CREATED);
    }

    //Atualiza resposta
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Resposta> updateResposta(@RequestBody Resposta resposta) {
        Resposta respostaSalva = respostaRepository.save(resposta);

        return new ResponseEntity<>(respostaSalva, HttpStatus.OK);
    }
    
    //Deleta resposta
    // ISSO NAO EH RESTFUL, pq precisa retornar uma resposta no formato json
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deleteResposta(@PathVariable("id") Long id) {
        respostaRepository.deleteById(id);

        return "ok";
    }
}
