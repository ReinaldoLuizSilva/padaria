package br.com.reinaldo.padaria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private double preco;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "validade")
    private LocalDate validade;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_produto",
                joinColumns = @JoinColumn(name="PRODUTO_ID"),
                inverseJoinColumns = @JoinColumn(name="pedido_id"))
    private List<Pedido> pedidos;

    public Produto(String nome, double preco, int quantidade, LocalDate validade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public Produto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
}
