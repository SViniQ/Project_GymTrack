package com.dereckportela.gymtracker.controller;
import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.dto.InstrutorDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
    private final InstrutorRepository instrutorRepository;
    public InstrutorController(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    @GetMapping
    public List<InstrutorDtoResponse> listar(){
        List<Instrutor> instrutores = instrutorRepository.findAll();
        return instrutores.stream().map(instrutor -> new InstrutorDtoResponse(instrutor.getId(), instrutor.getNome(), instrutor.getIdade(), instrutor.getMatricula(), instrutor.getEmail(), instrutor.getEspecialidade(), instrutor.getSexo(), instrutor.getSalario(), instrutor.getAlunos())).toList();
    }

    @PostMapping
    public Instrutor salvar(@RequestBody InstrutorDto dto) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(dto.getNome());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setEmail(dto.getEmail());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setCpf(dto.getCpf());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());

        return instrutorRepository.save(instrutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrutor> atualizarInstrutor(@PathVariable Long id, @RequestBody InstrutorDto dto) {
        Instrutor instrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor nao encontrado"));

        instrutor.setNome(dto.getNome());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setIdade(dto.getIdade());
        instrutor.setEmail(dto.getEmail());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setCpf(dto.getCpf());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());

        instrutorRepository.save(instrutor);

        return ResponseEntity.ok(instrutor);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instrutor> excluir(@PathVariable Long id) {
        if(!instrutorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Instrutor instrutor = instrutorRepository.findById(id).orElseThrow();
        if(!instrutor.getAlunos().isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        instrutorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
