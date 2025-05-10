package com.dereckportela.gymtracker.controller;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.dto.InstrutorDtoResponse;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
    private final InstructorService instructorService;

    public InstrutorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstrutorDtoResponse> listar() {
        return instructorService.listar();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid InstrutorDto dto) {
        try{
            Instrutor instrutor = instructorService.salvar(dto);
            InstrutorDtoResponse response = new InstrutorDtoResponse(
                    instrutor.getId(),
                    instrutor.getCpf(),
                    instrutor.getNome(),
                    instrutor.getIdade(),
                    instrutor.getMatricula(),
                    instrutor.getEmail(),
                    instrutor.getEspecialidade(),
                    instrutor.getSexo(),
                    instrutor.getSalario(),
                    instrutor.getAlunos());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("mensagem", e.getMessage())
            );
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInstrutor(@PathVariable Long id,
            @RequestBody @Valid InstrutorDto dto) {
        try{
            Instrutor instrutor = instructorService.atualizar(id, dto);
            InstrutorDtoResponse response = new InstrutorDtoResponse(
                    instrutor.getId(),
                    instrutor.getCpf(),
                    instrutor.getNome(),
                    instrutor.getIdade(),
                    instrutor.getMatricula(),
                    instrutor.getEmail(),
                    instrutor.getEspecialidade(),
                    instrutor.getSexo(),
                    instrutor.getSalario(),
                    instrutor.getAlunos());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("mensagem", e.getMessage())
            );
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            instructorService.remover(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
