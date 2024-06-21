package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record PedidoDTO(Integer id, String descricao,
                        @NotNull(message = "O campo totalPedido é obrigatório")
                        Double totalPedido,
                        @NotNull(message = "O campo quantidade é obrigatório")
                        Integer quantidade,
                        @NotNull(message = "O campo dataPedido é obrigatório")
                        String dataPedido,
                        @NotNull(message = "O campo dataEntrega é obrigatório")
                        @Future(message = "A data de entrega deve ser futura")
                        String dataEntrega,
                        Integer clienteId

) {}
