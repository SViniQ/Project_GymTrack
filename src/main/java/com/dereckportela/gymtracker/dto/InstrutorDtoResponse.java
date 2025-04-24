package com.dereckportela.gymtracker.dto;

import com.dereckportela.gymtracker.model.Instrutor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class InstrutorDtoResponse {
    private String nome;
    private int idade;
    private String especialidade;
    private List<String> alunos;

    public InstrutorDtoResponse(String nome, int idade, String especialidade, List<String> alunos) {
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.alunos = alunos;
    }

}
