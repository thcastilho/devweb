package br.unesp.rc.app.repository;

import br.unesp.rc.app.model.Genero;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GeneroRepository extends CrudRepository<Genero, Long>{
    @Query("select g from Genero g where g.name = ?1")
	Genero findByName(String name);   
}
