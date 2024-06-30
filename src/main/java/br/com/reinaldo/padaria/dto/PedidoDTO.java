package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(
        Integer id,
        @NotNull(message = "O campo totalPedido é obrigatório")
        @DecimalMin(value = "0.0", message = "O totalPedido deve ser maior que 0")
        Double totalPedido,
        @NotNull(message = "O campo dataPedido é obrigatório")
        @Future(message = "A data do pedido deve ser futura")
        LocalDate dataPedido,
        @NotNull(message = "O campo idCliente é obrigatório")
        Integer idCliente,
        @NotNull(message = "O campo idProdutos é obrigatório")
        List<Integer> idProdutos
) {}