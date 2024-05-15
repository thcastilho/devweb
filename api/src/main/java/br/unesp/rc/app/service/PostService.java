package br.unesp.rc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Genero;
import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.GeneroRepository;
import br.unesp.rc.app.repository.PostRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        return post;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = (List<Post>) postRepository.findAll();
        return posts;
    }

    public Post createPost(String token, Post post) {
        Usuario usuario = usuarioRepository.findByLogin(tokenService.validateToken(token.replace("Bearer ", "")));

        post.setUsuarioPost(usuario);
        
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        post.setUsuarioPost(postRepository.findById(id).get().getUsuarioPost());
        post.setId(id);
        Post postSalvo = postRepository.save(post);
        return postSalvo;
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        postRepository.delete(post);
    }

    public Post assignPostToGenre(Long idPost, Long idGenero) {
        Post post = postRepository.findById(idPost)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        
        Genero genero = generoRepository.findById(idGenero)
            .orElseThrow(() -> new IllegalArgumentException("Genero not found"));
        
        post.assignGenre(genero);
        postRepository.save(post);
        return post;
    }

    public void calcAverageRating(Long idPost) {
        Post post = postRepository.findById(idPost)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        List<Avaliacao> postAvaliacoes = post.getAvaliacoes();
        int totalNumStars = 0;
            
        for(Avaliacao a : postAvaliacoes) {
            totalNumStars += a.getNumStars();
        }

        float avgRating = (float) totalNumStars / postAvaliacoes.size();
        post.setAverageRating(avgRating);
        postRepository.save(post);
    }
}
