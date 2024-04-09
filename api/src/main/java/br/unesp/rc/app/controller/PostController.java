package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.repository.PostRepository;
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
        
    // Mostra post por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        try {
            Post post = postRepository.findById(id).get();
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }
    }
    
    // Mostra todos os posts
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> l = (List<Post>) postRepository.findAll();

        return new ResponseEntity<>(l, HttpStatus.OK);
    } 

    // Cria novo post
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post postSalvo = postRepository.save(post);

        return new ResponseEntity<>(postSalvo, HttpStatus.CREATED);
    }

    // Atualiza post
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post postSalvo = postRepository.save(post);

        return new ResponseEntity<>(postSalvo, HttpStatus.OK);
    }
    
    // Deleta post
    // Não é RESTful, porque precisa retornar uma usuario no formato json
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);

        return "ok";
    }
}
