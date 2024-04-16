package br.unesp.rc.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.unesp.rc.app.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    
}
