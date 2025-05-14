package com.dereckportela.gymtracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Alunos")
public class Aluno extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double peso;
    private double altura;
    private String objetivo;
    private double imc;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "instrutor_id")
        private Instrutor instrutor;

}
