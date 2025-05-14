package com.dereckportela.gymtracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class InstrutorDto {
    @NotBlank(message = "Nome não pode ser vazia!")
    private String nome;
    private Integer idade;
    private String especialidade;
    @NonNull
    private double salario;
    @NotBlank(message = "Email não pode ser vazio!")
    private String email;
    @NotBlank(message = "Telefone não pode ser vazia!")
    private String telefone;
    private String sexo;
    @NotBlank(message = "CPF não pode ser vazio!")
    private String cpf;
}
