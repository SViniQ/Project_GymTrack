package com.dereckportela.gymtracker.controller;
import com.dereckportela.gymtracker.dto.AlunoDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final InstrutorRepository instrutorRepository;
    private final AlunoRepository alunoRepository;

    public AlunoController(InstrutorRepository instrutorRepository, AlunoRepository alunoRepository) {

        this.instrutorRepository = instrutorRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public List<AlunoDtoResponse> listar(){
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream().map(aluno -> new AlunoDtoResponse(aluno.getNome(), aluno.getEmail(), aluno.getInstrutor() != null ? aluno.getInstrutor().getNome() : "Sem Instrutor")).toList();

    }
    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody AlunoDto dto) {
        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        Aluno aluno = new Aluno();
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
        Aluno saved = alunoRepository.save(aluno);

        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> excluir(@PathVariable Long id) {
        if(!alunoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        alunoRepository.deleteById(id);

    return ResponseEntity.ok().build();
    }


}