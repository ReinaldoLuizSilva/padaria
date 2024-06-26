package br.com.reinaldo.padaria.repositories;

import br.com.reinaldo.padaria.entities.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {}
