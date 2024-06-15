package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.ProdutoDTO;
import br.com.reinaldo.padaria.entities.Produto;
import br.com.reinaldo.padaria.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void salvarProduto(ProdutoDTO produto){
        Produto novoProduto = new Produto();
        novoProduto.setNome(produto.nome());
        novoProduto.setPreco(produto.preco());
        novoProduto.setQuantidade(produto.quantidade());
        novoProduto.setValidade(produto.validade());
        produtoRepository.save(novoProduto);
    }

    public Iterable<Produto> buscarTodosProdutos() {return produtoRepository.findAll();}

    public void editarForm(int id, Model model) {
        produtoRepository.findById(id).ifPresent(produto -> model.addAttribute("produto", produto));
    }

    public void editarSalvar(ProdutoDTO produto) {
        produtoRepository.findById(produto.id()).ifPresent(produto1 -> {
            produto1.setNome(produto.nome());
            produto1.setPreco(produto.preco());
            produto1.setQuantidade(produto.quantidade());
            produto1.setValidade(produto.validade());
            produtoRepository.save(produto1);
        });
    }

    public void deletar(@PathVariable("id") int id){
        produtoRepository.deleteById(id);
    }
}