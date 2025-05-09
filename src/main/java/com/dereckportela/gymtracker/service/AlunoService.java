package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.dto.AlunoDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;

    public AlunoService(AlunoRepository repository, InstrutorRepository instrutorRepository) {
        this.alunoRepository = repository;
        this.instrutorRepository = instrutorRepository;
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
        aluno.setMatricula(dto.getMatricula());
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setAltura(dto.getAltura());
        aluno.setPeso(dto.getPeso());
        aluno.setCpf(dto.getCpf());
        aluno.setObjetivo(dto.getObjetivo());
        aluno.setIdade(dto.getIdade());
        aluno.setSexo(dto.getSexo());
        aluno.setTelefone(dto.getTelefone());
        aluno.setInstrutor(instrutor);
        aluno.setImc(calculaIMC(aluno.getPeso(), aluno.getAltura()));
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, AlunoDto dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno nao encontrado"));
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());
        aluno.setAltura(dto.getAltura());
        aluno.setPeso(dto.getPeso());
        aluno.setCpf(dto.getCpf());
        aluno.setObjetivo(dto.getObjetivo());
        aluno.setIdade(dto.getIdade());
        aluno.setSexo(dto.getSexo());
        aluno.setTelefone(dto.getTelefone());
        aluno.setInstrutor(instrutor);
        aluno.setImc(calculaIMC(aluno.getPeso(), aluno.getAltura()));

        return alunoRepository.save(aluno);
    }

    public void remover(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));
        alunoRepository.delete(aluno);
    }

    private double calculaIMC(double peso, double altura){
        double imc = peso/(altura*altura);

        return Math.round(imc * 100.0) / 100.0;
    }
}
