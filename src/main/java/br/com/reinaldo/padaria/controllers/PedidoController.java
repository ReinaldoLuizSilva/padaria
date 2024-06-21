package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.ClienteDTO;
import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.entities.Cliente;
import br.com.reinaldo.padaria.entities.Pedido;
import br.com.reinaldo.padaria.services.ClienteService;
import br.com.reinaldo.padaria.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    ClienteService clienteService;

    @GetMapping("/novo")
    public String pedidoForm(@ModelAttribute("pedido") PedidoDTO pedido, Model model) {
        Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes); // Note que aqui é "clientes"
        return "/pedido/pedidoForm";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("pedidos", pedidoService.buscarTodosPedidos());
        return "/pedido/pedidoList";
    }

    @PostMapping("/novo")
    public String novoSalvar(@ModelAttribute("pedido") PedidoDTO pedido){
        pedidoService.salvarPedido(pedido);
        return "redirect:/pedido/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        PedidoDTO pedidoDTO = pedidoService.buscarPedidoPorId(id);
        model.addAttribute("pedido", pedidoDTO);
        Iterable<Cliente> clientes = clienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        return "/pedido/pedidoEditar";
    }

    @PostMapping("/editar")
    public String editarSalvar(@ModelAttribute("pedido") PedidoDTO pedidoDTO) {
        pedidoService.editarSalvar(pedidoDTO);
        return "redirect:/pedido/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPedido(@PathVariable("id") int id) {
        pedidoService.excluirPedido(id);
        return "redirect:/pedido/listar";
    }
}
