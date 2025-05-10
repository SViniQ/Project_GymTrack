package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.dto.InstrutorDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstrutorRepository instrutorRepository;
    private final ValidadorPessoa validadorPessoa;

    public InstructorService(InstrutorRepository instrutorRepository, ValidadorPessoa validadorPessoa) {
        this.instrutorRepository = instrutorRepository;
        this.validadorPessoa = validadorPessoa;
    }

    public List<InstrutorDtoResponse> listar() {
        List<Instrutor> instrutores = instrutorRepository.findAll();
        return instrutores.stream().map(instrutor -> new InstrutorDtoResponse(instrutor.getId(), instrutor.getCpf(), instrutor.getNome(), instrutor.getIdade(), instrutor.getMatricula(), instrutor.getEmail(), instrutor.getEspecialidade(), instrutor.getSexo(), instrutor.getSalario(), instrutor.getAlunos())).toList();
    }

    public Instrutor salvar(InstrutorDto dto) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(dto.getNome());
        instrutor.setIdade(dto.getIdade());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setEmail(dto.getEmail());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());
        try {
            validadorPessoa.validarCpfUnico(dto.getCpf());
            instrutor.setCpf(dto.getCpf());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao criar instrutor: " + e.getMessage());
        }

        return instrutorRepository.save(instrutor);
    }

    public Instrutor atualizar(Long id, InstrutorDto dto) {
        Instrutor instrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        instrutor.setNome(dto.getNome());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setIdade(dto.getIdade());
        instrutor.setEmail(dto.getEmail());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());
        if (!instrutor.getCpf().equals(dto.getCpf())) {
            try {
                validadorPessoa.validarCpfUnico(dto.getCpf());
                instrutor.setCpf(dto.getCpf());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Erro ao criar instrutor: " + e.getMessage());
            }
        }

        return instrutorRepository.save(instrutor);
    }

    public void remover(Long id) {
        Instrutor instrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor não encontrado"));

        if (!instrutor.getAlunos().isEmpty()) {
            throw new IllegalStateException("Não é possível excluir o instrutor enquanto houver alunos associados.");
        }

        instrutorRepository.deleteById(id);

    }

}
