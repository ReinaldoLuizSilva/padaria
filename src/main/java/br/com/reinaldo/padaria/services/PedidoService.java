package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.entities.Pedido;
import br.com.reinaldo.padaria.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Iterable<Pedido> buscarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public void salvarPedido(PedidoDTO pedido) {

        pedidoRepository.save(pedido);
    }
}
