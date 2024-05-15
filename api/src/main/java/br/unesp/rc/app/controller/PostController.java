package br.unesp.rc.app.controller;

import br.unesp.rc.app.model.Post;
import br.unesp.rc.app.service.PostService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;
    
    // Mostra post por Id
    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    // Mostra todos posts
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Post>> getAllPosts() {        
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    } 

    // Cria novo post
    @CrossOrigin("http:localhost:3000")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Post> createPost(@RequestHeader (name="Authorization") String token, @RequestBody Post post) {
        Post postSalvo = postService.createPost(token, post);
        return new ResponseEntity<>(postSalvo, HttpStatus.CREATED);
    }

    // Atualiza post
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post postSalvo = postService.updatePost(id, post);
        return new ResponseEntity<>(postSalvo, HttpStatus.OK);
    }

    // Deleta post
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Atribui um post a um gênero
    @PutMapping("/{idPost}/genero/{idGenero}")
    public ResponseEntity<Post> assignPostToGenre(@PathVariable Long idPost, @PathVariable Long idGenero) {
        Post post = postService.assignPostToGenre(idPost, idGenero);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // Calcula a média das avaliações do post
    @PutMapping("/average-rating/{id}")
    public ResponseEntity<Void> calcAverageRating(@PathVariable Long id) {
        postService.calcAverageRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
