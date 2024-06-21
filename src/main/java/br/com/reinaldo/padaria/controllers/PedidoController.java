package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.PedidoDTO;
import br.com.reinaldo.padaria.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/novo")
    public String pedidoForm(@ModelAttribute("pedido") PedidoDTO pedido){return "/pedido/pedidoForm";}

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
}
