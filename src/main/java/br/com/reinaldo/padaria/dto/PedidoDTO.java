package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


public record PedidoDTO(Integer id,
                        @NotNull(message = "O campo totalPedido é obrigatório")
                        Double totalPedido,
                        @NotNull(message = "O campo dataPedido é obrigatório")
                        LocalDate dataPedido,
                        Integer idCliente,
                        List<Integer> idProdutos
//                        List<String> nomesProdutos // Adicione esta linha
) {}
