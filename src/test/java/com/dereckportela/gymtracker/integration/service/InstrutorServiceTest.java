package com.dereckportela.gymtracker.integration.service;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.service.InstructorService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional



public class InstrutorServiceTest {
    @Autowired
    private InstrutorRepository instrutorRepository;
    @Autowired
    private InstructorService instructorService;
    private InstrutorDto instrutorDto;
    private Instrutor instrutor;

    @Test
    void deveSalvarInstrutorNoBanco(){
        instrutorDto = new InstrutorDto();
        instrutorDto.setMatricula("1234");
        instrutorDto.setNome("Carlos");
        instrutorDto.setIdade(40);
        instrutorDto.setEspecialidade("Crossfit");
        instrutorDto.setSalario(5000.0);
        instrutorDto.setEmail("carlos@academia.com");
        instrutorDto.setTelefone("99999-8888");
        instrutorDto.setSexo("M");
        instrutorDto.setEndereco("Rua da Academia");
        instrutorDto.setCpf("11122233344");

        instrutor = instructorService.salvar(instrutorDto);

        Optional<Instrutor> encontrado = instrutorRepository.findById(instrutor.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Carlos", encontrado.get().getNome());
    }

}
