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

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostRepository postRepository;

    public Avaliacao createAvaliacao(Long idPost, Usuario usuario, Avaliacao avaliacao) {
        Post post = postRepository.findById(idPost)
            .orElseThrow(() -> new RuntimeException("Post not found"));

        avaliacao.setAvaliacaoPost(post);
        avaliacao.setUsuarioAvaliacao(usuario);
        comentarioRepository.save(avaliacao);

        List<Avaliacao> avaliacoes = post.getAvaliacoes();
        double media = avaliacoes.stream().mapToDouble(Avaliacao::getNumStars).average().orElse(0.0);
        post.setAverageRating((float) media);
        postRepository.save(post);

        return avaliacao;
    }

    public Resposta createResposta(Long idAvaliacao, Usuario usuario, Resposta resposta) {
        Comentario avaliacao = comentarioRepository.findById(idAvaliacao)
            .orElseThrow(() -> new RuntimeException("Review not found"));
    
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
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        return post.getAvaliacoes();
    }

    public List<Avaliacao> getAvaliacoesByUser(Usuario usuario) {
        return usuario.getAvaliacoes();
    }

    public List<Resposta> getRespostasByAvaliacao(Long idAvaliacao) {
        Avaliacao avaliacao = (Avaliacao)comentarioRepository.findById(idAvaliacao)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        
        return avaliacao.getRespostas();
    }

    public List<Resposta> getRespostasByUser(Usuario usuario) {
        return usuario.getRespostas();
    }
}
