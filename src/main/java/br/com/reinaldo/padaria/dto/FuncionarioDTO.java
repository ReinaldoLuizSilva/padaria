package br.com.reinaldo.padaria.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record FuncionarioDTO(Integer id,
                             @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
                             String nome,
                             @Size(min = 10, max = 30, message = "O endereço deve ter entre 10 e 100 caracteres")
                             String endereco,
                             @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
                             String cpf,
                             @Size(min = 3, max = 30, message = "O cargo deve ter entre 3 e 100 caracteres")
                             String cargo,
                             @DecimalMin(value = "0.0", message = "O salário deve ser maior que 0")
                             Double salario
) {}