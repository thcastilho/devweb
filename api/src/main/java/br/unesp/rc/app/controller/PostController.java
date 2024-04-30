package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Avaliacao;
import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.model.Usuario;
import br.unesp.rc.app.repository.GeneroRepository;
import br.unesp.rc.app.repository.PostRepository;
import br.unesp.rc.app.repository.UsuarioRepository;

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
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Mostra post por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        try {
            Post post = postRepository.findById(id).get();
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such post", HttpStatus.NOT_FOUND);
        }
    }
    
    // Mostra todos posts
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> l = (List<Post>) postRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    // Cria novo post
    @PostMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<Post> createPost(@PathVariable Long idUsuario, @RequestBody Post post) {
        try{
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            post.setUsuarioPost(usuario);
            Post postSalvo = postRepository.save(post);
            System.out.println(post);
    
            return new ResponseEntity<>(postSalvo, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity("Failed request: ", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // Atualiza post
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        // if(postRepository.findById(id).isPresent()) {
        //     post.setUsuarioPost(postRepository.findById(id).get().getUsuarioPost());
        //     post.setId(id);
        //     Post postSalva = postRepository.save(post);
        //     return new ResponseEntity<>(postSalva, HttpStatus.OK);

        // } else return new ResponseEntity("Failed request: post " + id + " does not exists.", HttpStatus.NOT_FOUND);
        try {
            post.setUsuarioPost(postRepository.findById(id).get().getUsuarioPost());
            post.setId(id);
            Post postSalva = postRepository.save(post);
            return new ResponseEntity<>(postSalva, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity("Failed request: post " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }

    // Deleta post
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity deletePost(@PathVariable Long id) {
        try {
            Post post = postRepository.findById(id).get();
            postRepository.delete(post);

            return new ResponseEntity("Post " + id + " was deleted", HttpStatus.OK);

        } catch(Exception e) { 
            return new ResponseEntity("Failed request: post " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }

    // Atribui um post a um gênero
    @PutMapping("/{idPost}/genero/{idGenero}")
    public ResponseEntity assignPostToGenre(@PathVariable Long idPost, @PathVariable Long idGenero) {
        try {
            Post post = postRepository.findById(idPost).get();
            post.assignGenre(generoRepository.findById(idGenero).get());
            postRepository.save(post);
    
            return new ResponseEntity<>(post, HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity("Failed request: error while assigning a genre to a post.", HttpStatus.NOT_FOUND);
        }
    }

    // Calcula a média das avaliações do post
    @PutMapping("/{id}")
    public ResponseEntity calcAverageRating(@PathVariable Long id) {
        try {
            Post post = postRepository.findById(id).get();
            List<Avaliacao> postAvaliacoes = post.getAvaliacoes();
            int totalNumStars = 0;
            
            for(Avaliacao a : postAvaliacoes) {
                totalNumStars += a.getNumStars();
            }

            float avgRating = (float) totalNumStars / postAvaliacoes.size();
            post.setAverageRating(avgRating);
            postRepository.save(post);

            return new ResponseEntity<>("avgRating: " + avgRating, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error while calculating the average rating of that post.", HttpStatus.NOT_FOUND);
        }
    }
}
