package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlunoDtoResponse {
    private String nome;
    private String email;
    private String nomeInstrutor;

    public AlunoDtoResponse(String nome, String email, String nomeInstrutor) {
        this.nome = nome;
        this.email = email;
        this.nomeInstrutor = nomeInstrutor;
    }
}
