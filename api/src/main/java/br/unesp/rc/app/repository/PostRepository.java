package br.unesp.rc.app.repository;

import br.unesp.rc.app.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>{
    
}
