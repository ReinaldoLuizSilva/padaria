package br.com.reinaldo.padaria.dto;

public record FuncionarioDTO(Integer id,
                             String nome,
                             String endereco,
                             String cpf,
                             String cargo,
                             Double salario
) {}