package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlunoDtoResponse {
    private Long id;
    private String matricula;
    private String nome;
    private int idade;
    private String email;
    private String cpf;
    private String telefone;
    private String sexo;
    private double peso;
    private double altura;
    private double imc;
    private String objetivo;
    private String nomeInstrutor;

    public AlunoDtoResponse(Long id, String matricula, String nome, int idade, String email, String cpf, String telefone, String sexo, double peso, double altura, double imc, String objetivo, String nomeInstrutor) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.objetivo = objetivo;
        this.nomeInstrutor = nomeInstrutor;
    }
}
