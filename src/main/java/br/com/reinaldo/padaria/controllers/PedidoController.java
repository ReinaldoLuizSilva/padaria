package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.ClienteDTO;
import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.entities.Cliente;
import br.com.reinaldo.padaria.entities.Pedido;
import br.com.reinaldo.padaria.entities.Produto;
import br.com.reinaldo.padaria.services.ClienteService;
import br.com.reinaldo.padaria.services.PedidoService;
import br.com.reinaldo.padaria.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProdutoService produtoService;

    @GetMapping("/novo")
    public String pedidoForm(@ModelAttribute("pedido") PedidoDTO pedido, Model model) {
        Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
        Iterable<Produto> produtos = produtoService.buscarTodosProdutos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("produtos", produtos);
        return "/pedido/pedidoForm";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("pedidos", pedidoService.buscarTodosPedidos());
        return "/pedido/pedidoList";
    }

    @PostMapping("/novo")
    public String novoSalvar(@Valid @ModelAttribute("pedido") PedidoDTO pedido, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
            Iterable<Produto> produtos = produtoService.buscarTodosProdutos();
            model.addAttribute("clientes", clientes);
            model.addAttribute("produtos", produtos);
            return "/pedido/pedidoForm";
        }
        pedidoService.salvarPedido(pedido);
        return "redirect:/pedido/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        PedidoDTO pedidoDTO = pedidoService.buscarPedidoPorId(id);
        model.addAttribute("pedido", pedidoDTO);
        Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
        Iterable<Produto> produtos = produtoService.buscarTodosProdutos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("produtos", produtos);
        return "pedido/pedidoEditar";
    }

    @PostMapping("/editar/{id}")
    public String editarSalvar(@PathVariable("id") int id, @Valid @ModelAttribute("pedido") PedidoDTO pedidoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
            Iterable<Produto> produtos = produtoService.buscarTodosProdutos();
            model.addAttribute("clientes", clientes);
            model.addAttribute("produtos", produtos);
            return "pedido/pedidoEditar";
        }
        pedidoDTO = new PedidoDTO(id, pedidoDTO.totalPedido(), pedidoDTO.dataPedido(), pedidoDTO.idCliente(), pedidoDTO.idProdutos());
        pedidoService.editarSalvar(pedidoDTO);
        return "redirect:/pedido/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPedido(@PathVariable("id") int id) {
        pedidoService.excluirPedido(id);
        return "redirect:/pedido/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesPedido(@PathVariable("id") int id, Model model) {
        Pedido pedido = pedidoService.buscarPedidoDetalhe(id);
        model.addAttribute("pedido", pedido);
        return "/pedido/pedidoDetalhes";
    }

}
