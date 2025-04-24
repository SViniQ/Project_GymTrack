package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlunoDto {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;
    private String sexo;
    private double peso;
    private double altura;
    private int idade;
    private String objetivo;
    private Long instrutorId;
}
