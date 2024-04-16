package br.unesp.rc.app.controller;

import org.apache.catalina.connector.Response;
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
import br.unesp.rc.app.model.Comentario;
import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.ComentarioRepository;
import br.unesp.rc.app.repository.PostRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/avaliacao/{idPost}/{idUsuario}")
    public ResponseEntity<Avaliacao> createAvaliacao(@PathVariable Long idPost, @PathVariable Long idUsuario, @RequestBody Avaliacao avaliacao) {
        Post post = postRepository.findById(idPost).get();
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        avaliacao.setAvaliacaoPost(post);
        avaliacao.setUsuarioAvaliacao(usuario);
        comentarioRepository.save(avaliacao);
        return new ResponseEntity<>(avaliacao, HttpStatus.CREATED);
    }

    @PostMapping("/resposta/{idAvaliacao}/{idUsuario}")
    public ResponseEntity<Resposta> createResposta(@PathVariable Long idAvaliacao, @PathVariable Long idUsuario, @RequestBody Resposta resposta) {
        Comentario avaliacao = comentarioRepository.findById(idAvaliacao).get();
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        resposta.setAvaliacaoResposta((Avaliacao)avaliacao);
        resposta.setUsuarioResposta(usuario);
        comentarioRepository.save(resposta);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComentario(@PathVariable Long id) {
        comentarioRepository.deleteById(id);
        return new ResponseEntity<>("comentario " + id + " deletado", HttpStatus.OK);
    }
}
