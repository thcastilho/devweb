package br.unesp.rc.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.unesp.rc.app.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    Optional<Post> findFirstByNameContainingIgnoreCase(String title);
}
