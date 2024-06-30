package br.com.reinaldo.padaria.repositories;

import br.com.reinaldo.padaria.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public Usuario findByUsername(String username);

}