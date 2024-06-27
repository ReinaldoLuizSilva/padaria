package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.entities.Pedido;
import br.com.reinaldo.padaria.entities.Produto;
import br.com.reinaldo.padaria.repositories.PedidoRepository;
import br.com.reinaldo.padaria.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProdutoRepository produtoRepository;

    public Iterable<Pedido> buscarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public void salvarPedido(PedidoDTO pedido) {
        Pedido novoPedido = new Pedido();
        novoPedido.setTotalPedido(pedido.totalPedido());
        novoPedido.setDataPedido(pedido.dataPedido());
        clienteService.buscarClientePorId(pedido.idCliente()).ifPresent(novoPedido::setCliente);

        // Adicione esta lógica para salvar os produtos
        List<Produto> produtos = (List<Produto>) produtoRepository.findAllById(pedido.idProdutos());
        novoPedido.setProdutos(produtos);

        pedidoRepository.save(novoPedido);
    }

    public void editarSalvar(PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoDTO.id());
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setTotalPedido(pedidoDTO.totalPedido());
            pedido.setDataPedido(pedidoDTO.dataPedido());
            clienteService.buscarClientePorId(pedidoDTO.idCliente()).ifPresent(pedido::setCliente);

            List<Produto> produtos = (List<Produto>) produtoRepository.findAllById(pedidoDTO.idProdutos());
            pedido.setProdutos(produtos);

            pedidoRepository.save(pedido);
        } else {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + pedidoDTO.id());
        }
    }

    public Pedido buscarPedidoDetalhe(int id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));
    }

    public PedidoDTO buscarPedidoPorId(int id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));
        List<Integer> idProdutos = pedido.getProdutos().stream().map(Produto::getId).toList();
        return new PedidoDTO(pedido.getId(), pedido.getTotalPedido(), pedido.getDataPedido(), pedido.getCliente().getId(), idProdutos);
    }

    public void excluirPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}