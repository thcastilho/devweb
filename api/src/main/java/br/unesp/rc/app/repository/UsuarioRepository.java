package br.unesp.rc.app.repository;

import br.unesp.rc.app.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    @Query("select u from Usuario u where u.username = ?1")
    Usuario findByUsername(String username);
}
