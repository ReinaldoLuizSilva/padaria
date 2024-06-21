package br.com.reinaldo.padaria.controllers;


import br.com.reinaldo.padaria.dto.FuncionarioDTO;
import br.com.reinaldo.padaria.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/novo")
    public String funcionarioForm(@ModelAttribute("funcionario") FuncionarioDTO funcionario){return "/funcionario/funcionarioForm";}

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodosFuncionarios());
        return "/funcionario/funcionarioList";
    }

    @PostMapping("/novo")
    public String novoSalvar(@ModelAttribute("funcionario") FuncionarioDTO funcionario){
        funcionarioService.salvarFuncionario(funcionario);
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        funcionarioService.editarForm(id, model);
        return "funcionario/funcionarioEditar"; // sem a barra inicial
    }

    @PostMapping("/editar")
    public String editarSalvar(@ModelAttribute("funcionario") FuncionarioDTO funcionario){
        funcionarioService.editarSalvar(funcionario);
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id){
        funcionarioService.deletar(id);
        return "redirect:/funcionario/listar";
    }
}
