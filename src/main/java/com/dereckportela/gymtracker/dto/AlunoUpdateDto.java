package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlunoUpdateDto {
    private String matricula;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private int idade;
    private String sexo;
    private String objetivo;
    private Long instrutorId;
    private double altura;
    private double peso;
}
