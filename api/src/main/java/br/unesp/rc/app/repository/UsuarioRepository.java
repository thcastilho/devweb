package br.unesp.rc.app.repository;

import br.unesp.rc.app.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    
}
