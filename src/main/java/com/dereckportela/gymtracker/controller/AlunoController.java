package com.dereckportela.gymtracker.controller;

import com.dereckportela.gymtracker.dto.AlunoDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.unit.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final InstrutorRepository instrutorRepository;
    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService, InstrutorRepository instrutorRepository, AlunoRepository alunoRepository) {
        this.alunoService = alunoService;
        this.instrutorRepository = instrutorRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public List<AlunoDtoResponse> listar() {
        return alunoService.listar();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody AlunoDto dto) {
        try {
            Aluno aluno = alunoService.cadastrar(dto);
            AlunoDtoResponse reponse = new AlunoDtoResponse(
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
                    aluno.getObjetivo(),
                    aluno.getInstrutor().getNome()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("mensagem", e.getMessage())
            );
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDto dto) {
        try {
            Aluno aluno = alunoService.atualizar(id, dto);
            AlunoDtoResponse reponse = new AlunoDtoResponse(
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
                    aluno.getObjetivo(),
                    aluno.getInstrutor().getNome()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(reponse);

        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("mensagem", e.getMessage())
            );

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            alunoService.remover(id);
            return ResponseEntity.ok().build();
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }


}