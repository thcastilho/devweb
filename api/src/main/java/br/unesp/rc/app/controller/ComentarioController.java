package br.unesp.rc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.app.dto.CreateAvaliacaoDTO;
import br.unesp.rc.app.dto.CreateRespostaDTO;
import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.service.ComentarioService;
import br.unesp.rc.app.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/avaliacao/{idPost}")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestHeader (name="Authorization") String token, @PathVariable Long idPost, @RequestBody @Valid CreateAvaliacaoDTO avaliacao) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        Avaliacao novaAvaliacao = comentarioService.createAvaliacao(idPost, usuario, avaliacao);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/resposta/{idAvaliacao}")
    public ResponseEntity<Resposta> createResposta(@RequestHeader (name="Authorization") String token, @PathVariable Long idAvaliacao, @RequestBody @Valid CreateRespostaDTO resposta) {
        Usuario usuario = usuarioService.getUsuarioByToken(token);
        Resposta novaResposta = comentarioService.createResposta(idAvaliacao, usuario, resposta);
        return new ResponseEntity<>(novaResposta, HttpStatus.CREATED);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
    //     comentarioService.deleteById(id);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/{idPost}/avaliacoes")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByPost(@PathVariable Long idPost) {
        List<Avaliacao> avaliacoes = comentarioService.getAvaliacoesByPost(idPost);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    // @GetMapping("/avaliacoes")
    // public ResponseEntity<List<Avaliacao>> getAvaliacoesByUser(@RequestHeader (name="Authorization") String token) {
    //     Usuario usuario = usuarioService.getUsuarioByToken(token);
    //     List<Avaliacao> avaliacoes = comentarioService.getAvaliacoesByUser(usuario);
    //     return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    // }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/{idAvaliacao}/respostas")
    public ResponseEntity<List<Resposta>> getRespostasByAvaliacao(@PathVariable Long idAvaliacao) {
        List<Resposta> respostas = comentarioService.getRespostasByAvaliacao(idAvaliacao);
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }

    // @GetMapping("/respostas/{idUsuario}")
    // public ResponseEntity<List<Resposta>> getRespostasByUser(@RequestHeader (name="Authorization") String token) {
    //     Usuario usuario = usuarioService.getUsuarioByToken(token);
    //     List<Resposta> respostas = comentarioService.getRespostasByUser(usuario);
    //     return new ResponseEntity<>(respostas, HttpStatus.OK);
    // }
}
