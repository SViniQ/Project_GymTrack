package com.dereckportela.gymtracker.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class InstrutorDto {
  //  @NonNull
    private  String matricula;
    private String nome;
    private Integer idade;
    private String especialidade;
   // @NonNull
    private double salario;
   // @NonNull
    private String email;
   // @NonNull
    private String telefone;
    private String sexo;
   // @NonNull
    private String endereco;
   // @NonNull
    private String cpf;
}
