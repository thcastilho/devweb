package br.unesp.rc.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.unesp.rc.app.model.Comentario;

public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
    
}
