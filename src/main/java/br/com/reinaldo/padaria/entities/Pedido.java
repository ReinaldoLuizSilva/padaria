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

    public Pedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_produto",
                joinColumns = @JoinColumn(name="pedido_id"),
                inverseJoinColumns = @JoinColumn(name="produto_id"))
    private List<Produto> produtos;

    @OneToMany(cascade = CascadeType.ALL,
                mappedBy = "pedido")
    private List<Cliente> clientes;

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
}
