package br.com.reinaldo.padaria.repositories;

import br.com.reinaldo.padaria.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {}
