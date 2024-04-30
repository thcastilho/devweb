package br.unesp.rc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/avaliacao/{idPost}/{idUsuario}")
    public ResponseEntity<Avaliacao> createAvaliacao(@PathVariable Long idPost, @PathVariable Long idUsuario, @RequestBody Avaliacao avaliacao) {
        comentarioService.createAvaliacao(idPost, idUsuario, avaliacao);
        return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
    }

    @PostMapping("/resposta/{idAvaliacao}/{idUsuario}")
    public ResponseEntity<Resposta> createResposta(@PathVariable Long idAvaliacao, @PathVariable Long idUsuario, @RequestBody Resposta resposta) {
        comentarioService.createResposta(idAvaliacao, idUsuario, resposta);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{idPost}/avaliacoes")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByPost(@PathVariable Long idPost) {
        List<Avaliacao> avaliacoes = comentarioService.getAvaliacoesByPost(idPost);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping("/avaliacoes/{idUsuario}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByUser(@PathVariable Long idUsuario) {
        List<Avaliacao> avaliacoes = comentarioService.getAvaliacoesByUser(idUsuario);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping("/{idAvaliacao}/respostas")
    public ResponseEntity<List<Resposta>> getRespostasByAvaliacao(@PathVariable Long idAvaliacao) {
        List<Resposta> respostas = comentarioService.getRespostasByAvaliacao(idAvaliacao);
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }

    @GetMapping("/respostas/{idUsuario}")
    public ResponseEntity<List<Resposta>> getRespostasByUser(@PathVariable Long idUsuario) {
        List<Resposta> respostas = comentarioService.getRespostasByUser(idUsuario);
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }
}
