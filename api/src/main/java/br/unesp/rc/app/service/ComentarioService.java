package br.unesp.rc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Comentario;
import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.model.Resposta;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.ComentarioRepository;
import br.unesp.rc.app.repository.PostRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    public Avaliacao createAvaliacao(Long idPost, Usuario usuario, Avaliacao avaliacao) {
        Post post = postRepository.findById(idPost)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        avaliacao.setAvaliacaoPost(post);
        avaliacao.setUsuarioAvaliacao(usuario);
        comentarioRepository.save(avaliacao);

        return avaliacao;
    }

    public Resposta createResposta(Long idAvaliacao, Usuario usuario, Resposta resposta) {
        Comentario avaliacao = comentarioRepository.findById(idAvaliacao)
            .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    
        resposta.setAvaliacaoResposta((Avaliacao)avaliacao);
        resposta.setUsuarioResposta(usuario);
        comentarioRepository.save(resposta);
        
        return resposta;
    }

    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }

    public List<Avaliacao> getAvaliacoesByPost(Long idPost) {
        Post post = postRepository.findById(idPost)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        
        return post.getAvaliacoes();
    }

    public List<Avaliacao> getAvaliacoesByUser(Usuario usuario) {
        return usuario.getAvaliacoes();
    }

    public List<Resposta> getRespostasByAvaliacao(Long idAvaliacao) {
        Avaliacao avaliacao = (Avaliacao)comentarioRepository.findById(idAvaliacao)
            .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        
        return avaliacao.getRespostas();
    }

    public List<Resposta> getRespostasByUser(Usuario usuario) {
        return usuario.getRespostas();
    }
}
