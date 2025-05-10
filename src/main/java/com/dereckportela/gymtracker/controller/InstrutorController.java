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
    public ResponseEntity<InstrutorDtoResponse> salvar(@RequestBody @Valid InstrutorDto dto) {
        Instrutor instrutor = instructorService.salvar(dto);
        InstrutorDtoResponse response = new InstrutorDtoResponse(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getIdade(),
                instrutor.getMatricula(),
                instrutor.getEmail(),
                instrutor.getEspecialidade(),
                instrutor.getSexo(),
                instrutor.getSalario(),
                instrutor.getAlunos());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrutorDtoResponse> atualizarInstrutor(@PathVariable Long id,
            @RequestBody @Valid InstrutorDto dto) {
        Instrutor instrutor = instructorService.atualizar(id, dto);
        InstrutorDtoResponse response = new InstrutorDtoResponse(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getIdade(),
                instrutor.getMatricula(),
                instrutor.getEmail(),
                instrutor.getEspecialidade(),
                instrutor.getSexo(),
                instrutor.getSalario(),
                instrutor.getAlunos());

        return ResponseEntity.ok(response);

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
