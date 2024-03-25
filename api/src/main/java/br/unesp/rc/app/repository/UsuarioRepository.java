package br.unesp.rc.app.repository;



import org.springframework.data.repository.CrudRepository;

import br.unesp.rc.app.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    
}
