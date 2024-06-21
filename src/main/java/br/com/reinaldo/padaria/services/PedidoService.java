package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.entities.Pedido;
import br.com.reinaldo.padaria.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteService clienteService;

    public Iterable<Pedido> buscarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public void salvarPedido(PedidoDTO pedido) {
        Pedido novoPedido = new Pedido();
        novoPedido.setTotalPedido(pedido.totalPedido());
        novoPedido.setDataPedido(pedido.dataPedido());
        clienteService.buscarClientePorId(pedido.idCliente()).ifPresent(novoPedido::setCliente);
        pedidoRepository.save(novoPedido);
    }

    public void editarSalvar(PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoDTO.id());
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setTotalPedido(pedidoDTO.totalPedido());
            pedido.setDataPedido(pedidoDTO.dataPedido());
            clienteService.buscarClientePorId(pedidoDTO.idCliente()).ifPresent(pedido::setCliente);
            pedidoRepository.save(pedido);
        } else throw new EntityNotFoundException("Pedido não encontrado com o ID: " + pedidoDTO.id());

    }

    public PedidoDTO buscarPedidoPorId(int id){
        Pedido pedido = pedidoRepository.findById(id).get();
        PedidoDTO pedidoDTO = new PedidoDTO(pedido.getId(), pedido.getTotalPedido(), pedido.getDataPedido(), pedido.getCliente().getId());
        return pedidoDTO;
    }

    public void excluirPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}