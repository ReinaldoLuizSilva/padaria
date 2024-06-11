package br.com.reinaldo.padaria.services;

import br.com.reinaldo.padaria.dto.ProdutoDTO;
import br.com.reinaldo.padaria.entities.Produto;
import br.com.reinaldo.padaria.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }
}
