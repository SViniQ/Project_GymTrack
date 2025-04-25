package com.dereckportela.gymtracker.dto;

import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class InstrutorDtoResponse {
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String sexo;
    private int idade;
    private double salario;
    private String especialidade;
    private List<Aluno> alunos;

    public InstrutorDtoResponse(Long id, String nome, int idade, String matricula, String email, String especialidade, String sexo, double salario, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
        this.email = email;
        this.sexo = sexo;
        this.salario = salario;
        this.especialidade = especialidade;
        this.alunos = alunos;
    }

}
