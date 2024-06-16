package br.com.reinaldo.padaria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total_pedido")
    private double totalPedido;

    public Pedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_produto",
                joinColumns = @JoinColumn(name="pedido_id"),
                inverseJoinColumns = @JoinColumn(name="produto_id"))
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", totalPedido=" + totalPedido +
                ", produtos=" + produtos +
                ", cliente=" + cliente +
                '}';
    }
}
