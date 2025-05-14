package com.dereckportela.gymtracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class AlunoDto {
    @NotBlank(message = "Nome não pode ser vazio!")
    private String nome;
    @NotBlank(message = "Email não pode ser vazio!")
    private String email;
    @NotBlank(message = "Telefon não pode ser vazio!")
    private String telefone;
    @NotBlank(message = "CPF não pode ser vazio!")
    private String cpf;
    private String sexo;
    private double peso;
    private double altura;
    private int idade;
    private String objetivo;
    @NonNull
    private Long instrutorId;
}
