package br.unesp.rc.app.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unesp.rc.app.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    @Query("select u from Usuario u where u.username = ?1")
    Usuario findByUsername(String username);
}
