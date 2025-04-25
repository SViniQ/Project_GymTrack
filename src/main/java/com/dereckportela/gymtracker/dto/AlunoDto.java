package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class AlunoDto {
    @NonNull
    private String matricula;
    @NonNull
    private String nome;
    @NonNull
    private String email;
    @NonNull
    private String telefone;
    @NonNull
    private String endereco;
    @NonNull
    private String cpf;
    private String sexo;
    @NonNull
    private double peso;
    @NonNull
    private double altura;
    private int idade;
    private String objetivo;
    private Long instrutorId;
}
