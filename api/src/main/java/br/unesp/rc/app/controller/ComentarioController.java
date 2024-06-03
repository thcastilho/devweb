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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.service.ComentarioService;
import br.unesp.rc.app.service.UsuarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/avaliacao/{idPost}")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestHeader (name="Authorization") String token, @PathVariable Long idPost, @RequestBody Avaliacao avaliacao) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        comentarioService.createAvaliacao(idPost, usuario, avaliacao);
        return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
    }

    @PostMapping("/resposta/{idAvaliacao}")
    public ResponseEntity<Resposta> createResposta(@RequestHeader (name="Authorization") String token, @PathVariable Long idAvaliacao, @RequestBody Resposta resposta) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        comentarioService.createResposta(idAvaliacao, usuario, resposta);
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

    @GetMapping("/avaliacoes")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByUser(@RequestHeader (name="Authorization") String token) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        List<Avaliacao> avaliacoes = comentarioService.getAvaliacoesByUser(usuario);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping("/{idAvaliacao}/respostas")
    public ResponseEntity<List<Resposta>> getRespostasByAvaliacao(@PathVariable Long idAvaliacao) {
        List<Resposta> respostas = comentarioService.getRespostasByAvaliacao(idAvaliacao);
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }

    @GetMapping("/respostas/{idUsuario}")
    public ResponseEntity<List<Resposta>> getRespostasByUser(@RequestHeader (name="Authorization") String token) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        List<Resposta> respostas = comentarioService.getRespostasByUser(usuario);
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }
}
