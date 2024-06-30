package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.ProdutoDTO;
import br.com.reinaldo.padaria.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/novo")
    public String produtoForm(@ModelAttribute("produto") ProdutoDTO produto){
        return "/produto/produtoForm";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("produtos", produtoService.buscarTodosProdutos());
        return "/produto/produtoList";
    }

    @PostMapping("/novo")
    public String novoSalvar(@Valid @ModelAttribute("produto") ProdutoDTO produto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "/produto/produtoForm";
        produtoService.salvarProduto(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") int id, Model model) {
        produtoService.editarForm(id, model);
        return "/produto/produtoEditar";
    }

    @PostMapping("/editar")
    public String editarSalvar(@Valid @ModelAttribute("produto") ProdutoDTO produto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "/produto/produtoEditar";
        produtoService.editarSalvar(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id){
        produtoService.deletar(id);
        return "redirect:/produto/listar";
    }

    @GetMapping("/nome")
    public String buscarPorNome(@RequestParam(value = "nome", required = false) String nome, Model model){
        if (nome == null || nome.isEmpty()) {
            model.addAttribute("produtos", produtoService.buscarTodosProdutos());
        } else {
            model.addAttribute("produtos", produtoService.buscarPorNome(nome));
        }
        return "/produto/produtoNome";
    }
}
