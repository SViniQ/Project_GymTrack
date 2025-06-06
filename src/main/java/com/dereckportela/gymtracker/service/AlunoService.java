package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.dto.AlunoDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.util.CalculadoraIMC;
import com.dereckportela.gymtracker.util.GeradorMatricula;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final ValidadorPessoa validadorPessoa;
    private String matricula;

    public AlunoService(AlunoRepository repository, InstrutorRepository instrutorRepository, ValidadorPessoa validadorPessoa) {
        this.alunoRepository = repository;
        this.instrutorRepository = instrutorRepository;
        this.validadorPessoa = validadorPessoa;
    }

    public List<AlunoDtoResponse> listar() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream()
                .map(aluno -> new AlunoDtoResponse(
                        aluno.getId(),
                        aluno.getMatricula(),
                        aluno.getNome(),
                        aluno.getIdade(),
                        aluno.getEmail(),
                        aluno.getCpf(),
                        aluno.getTelefone(),
                        aluno.getSexo(),
                        aluno.getPeso(),
                        aluno.getAltura(),
                        aluno.getImc(),
                        aluno.getObjetivo(),
                        aluno.getInstrutor() != null ? aluno.getInstrutor().getNome() : "Sem Instrutor"
                ))
                .toList();
    }

    public Aluno cadastrar(AlunoDto dto) {
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        Aluno aluno = new Aluno();
        matricula = GeradorMatricula.gerarMatricula();
        aluno.setMatricula(matricula);
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setAltura(dto.getAltura());
        aluno.setPeso(dto.getPeso());
        aluno.setObjetivo(dto.getObjetivo());
        aluno.setIdade(dto.getIdade());
        aluno.setSexo(dto.getSexo());
        aluno.setTelefone(dto.getTelefone());
        aluno.setInstrutor(instrutor);
        try {
            validadorPessoa.validarCpfUnico(dto.getCpf());
            aluno.setCpf(dto.getCpf());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao criar Aluno: " + e.getMessage());
        }
        try {
            aluno.setImc(CalculadoraIMC.calcularIMC(aluno.getPeso(), aluno.getAltura()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao calcular IMC: " + e.getMessage());
        }

        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, AlunoDto dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno nao encontrado"));
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setMatricula(matricula);
        aluno.setEmail(dto.getEmail());
        aluno.setAltura(dto.getAltura());
        aluno.setPeso(dto.getPeso());
        aluno.setObjetivo(dto.getObjetivo());
        aluno.setIdade(dto.getIdade());
        aluno.setSexo(dto.getSexo());
        aluno.setTelefone(dto.getTelefone());
        aluno.setInstrutor(instrutor);
        if (!dto.getCpf().equals(aluno.getCpf())) {
            try {
                validadorPessoa.validarCpfUnico(dto.getCpf());
                aluno.setCpf(dto.getCpf());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Erro ao criar aluno: " + e.getMessage());
            }
        }
        try {
            aluno.setImc(CalculadoraIMC.calcularIMC(aluno.getPeso(), aluno.getAltura()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao calcular IMC: " + e.getMessage());
        }

        return alunoRepository.save(aluno);
    }

    public void remover(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));
        alunoRepository.delete(aluno);
    }

}
