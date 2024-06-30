package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProdutoDTO(Integer id,
                         @Size(min = 3, max = 40, message = "O nome deve ter entre 3 e 40 caracteres")
                         String nome,
                         @NotNull(message = "O preço é obrigatório")
                         @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
                         Double preco,
                         @NotNull(message = "A quantidade é obrigatória")
                         @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
                         Integer quantidade,
                         @Future(message = "A validade deve ser uma data futura")
                         LocalDate validade) {}