package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.ClienteDTO;
import br.com.reinaldo.padaria.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/novo")
    public String clienteForm(@ModelAttribute("cliente") ClienteDTO cliente){return "/cliente/clienteForm";}

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("clientes", clienteService.buscarTodosClientes());
        return "/cliente/clienteList";
    }

    @PostMapping("/novo")
    public String novoSalvar(@Valid @ModelAttribute("cliente") ClienteDTO cliente, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "/cliente/clienteForm";
        clienteService.salvarCliente(cliente);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        clienteService.editarForm(id, model);
        return "/cliente/clienteEditar";
    }

    @PostMapping("/editar")
    public String editarSalvar(ClienteDTO cliente){
        clienteService.editarSalvar(cliente);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id){
        clienteService.deletar(id);
        return "redirect:/cliente/listar";
    }
}
