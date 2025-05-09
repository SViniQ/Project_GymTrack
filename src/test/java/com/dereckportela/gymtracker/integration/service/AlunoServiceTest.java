package com.dereckportela.gymtracker.integration.service;

import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.service.AlunoService;
import com.dereckportela.gymtracker.service.InstructorService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional

public class AlunoServiceTest {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private InstrutorRepository instrutorRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private InstructorService instructorService;
    private InstrutorDto instrutorDto;
    private Instrutor instrutor;
    private AlunoDto alunoDto;
    private Aluno aluno;

    @BeforeEach
    public void setUp() {
        instrutor = new Instrutor();
        instrutor.setNome("Carlos");
        instrutor.setEspecialidade("Musculação");
        instrutor.setEmail("carlos@exemplo.com");
        instrutor.setMatricula("I001");
        instrutor.setSalario(3000.0);
        instrutor.setCpf("12345678900");
        instrutor.setTelefone("99999-0000");
        instrutor.setSexo("M");
        instrutor.setIdade(35);

        instrutor = instrutorRepository.save(instrutor);

    }

    @Test
    void deveSalvarAlunoNoBanco(){
        alunoDto = new AlunoDto();
        alunoDto.setMatricula("A123");
        alunoDto.setNome("João");
        alunoDto.setEmail("joao@exemplo.com");
        alunoDto.setTelefone("99999-0000");
        alunoDto.setCpf("12345678900");
        alunoDto.setSexo("M");
        alunoDto.setPeso(75.0);
        alunoDto.setAltura(1.80);
        alunoDto.setIdade(25);
        alunoDto.setObjetivo("Ganhar massa");
        alunoDto.setInstrutorId(instrutor.getId());

        Aluno alunoSalvo = alunoService.cadastrar(alunoDto);

        assertNotNull(alunoSalvo.getId());
        assertEquals("João", alunoSalvo.getNome());
        assertEquals(instrutor.getId(), alunoSalvo.getInstrutor().getId());
        List<Aluno> alunos = alunoRepository.findAll();
        assertEquals(1, alunos.size());
    }
}
