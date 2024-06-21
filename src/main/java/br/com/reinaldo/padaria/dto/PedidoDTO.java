package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoDTO(Integer id,
                        @NotNull(message = "O campo totalPedido é obrigatório")
                        Double totalPedido,
                        @NotNull(message = "O campo dataPedido é obrigatório")
                        LocalDate dataPedido,
                        Integer idCliente
) {}
