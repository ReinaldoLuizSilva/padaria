package br.com.reinaldo.padaria.repositories;

import br.com.reinaldo.padaria.entities.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
}
