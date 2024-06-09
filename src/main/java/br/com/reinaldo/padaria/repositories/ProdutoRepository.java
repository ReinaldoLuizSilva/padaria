package br.com.reinaldo.padaria.repositories;

import br.com.reinaldo.padaria.entities.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {}