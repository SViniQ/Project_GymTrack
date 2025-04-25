package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlunoDtoResponse {
    private Long id;
    private String matricula;
    private String nome;
    private String email;
    private double peso;
    private double altura;
    private String objetivo;
    private String nomeInstrutor;

    public AlunoDtoResponse(Long id, String matricula, String nome, String email, double peso, double altura, String objetivo, String nomeInstrutor) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.nomeInstrutor = nomeInstrutor;
    }
}
