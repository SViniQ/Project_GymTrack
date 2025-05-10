package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPessoa {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private InstrutorRepository instrutorRepository;

    public void validarCpfUnico(String cpf) {
        boolean existeAluno = alunoRepository.existsByCpf(cpf);
        boolean existeInstrutor = instrutorRepository.existsByCpf(cpf);

        if (existeAluno || existeInstrutor) {
            throw new IllegalArgumentException("Já existe uma pessoa com esse CPF");
        }
    }
}
