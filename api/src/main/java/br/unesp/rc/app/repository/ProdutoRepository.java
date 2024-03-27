package br.unesp.rc.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.unesp.rc.app.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    
}
