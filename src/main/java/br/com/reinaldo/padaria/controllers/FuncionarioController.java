package br.com.reinaldo.padaria.controllers;


import br.com.reinaldo.padaria.dto.FuncionarioDTO;
import br.com.reinaldo.padaria.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String novoSalvar(@ModelAttribute("funcionario") @Valid FuncionarioDTO funcionario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return "/funcionario/funcionarioForm";
        }
        funcionarioService.salvarFuncionario(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso!");
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        funcionarioService.editarForm(id, model);
        return "funcionario/funcionarioEditar"; // sem a barra inicial
    }

    @PostMapping("/editar")
    public String editarSalvar(@ModelAttribute("funcionario") @Valid FuncionarioDTO funcionario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return "funcionario/funcionarioEditar";
        }
        funcionarioService.editarSalvar(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionario editado com sucesso!");
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id){
        funcionarioService.deletar(id);
        return "redirect:/funcionario/listar";
    }
}
