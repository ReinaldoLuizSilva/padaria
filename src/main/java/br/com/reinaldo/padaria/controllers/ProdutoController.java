package br.com.reinaldo.padaria.controllers;

import br.com.reinaldo.padaria.dto.ProdutoDTO;
import br.com.reinaldo.padaria.entities.Produto;
import br.com.reinaldo.padaria.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/novo")
    public String produtoForm(@ModelAttribute("produto") ProdutoDTO produto){
        return "/produto/produtoForm";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("produtos", produtoRepository.findAll());
        return "/produto/produtoListar";
    }

    @PostMapping("/novo")
    public String novoSalvar(@Valid @ModelAttribute("produto") ProdutoDTO produto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/produto/produtoForm";
        }
        Produto novoProduto = new Produto();
        novoProduto.setNome(produto.nome());
        novoProduto.setPreco(produto.preco());
        novoProduto.setQuantidade(produto.quantidade());
        novoProduto.setValidade(produto.validade());
        produtoRepository.save(novoProduto);
        return "redirect:/produto/listar";
    }
}
