package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.Size;

public record ClienteDTO(Integer id,
                         @Size(min = 5, max = 40, message = "O nome deve ter entre 5 e 40 caracteres")
                         String nome,
                         @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
                         String telefone,
                         @Size(min = 5, max = 100, message = "O endereço deve ter entre 5 e 100 caracteres")
                         String endereco) {}
