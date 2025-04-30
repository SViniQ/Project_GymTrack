package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.service.InstructorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InstrutorServiceTest {

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private InstructorService instrutorService;

    @Test
    void deveCadastrarInstrutorComSucesso() {
        InstrutorDto dto = new InstrutorDto();
        dto.setNome("João");
        dto.setMatricula("1234");
        dto.setEmail("joao@exemplo.com");
        dto.setCpf("12345678900");
        dto.setTelefone("123456789");
        dto.setSexo("M");
        dto.setSalario(3000.0);
        dto.setEspecialidade("Musculação");
        dto.setEndereco("Rua do Sul");
        dto.setIdade(31);

        Instrutor instrutor = new Instrutor();
        instrutor.setId(1L);
        instrutor.setNome(dto.getNome());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setEmail(dto.getEmail());
        instrutor.setCpf(dto.getCpf());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());

        when(instrutorRepository.save(any(Instrutor.class))).thenReturn(instrutor);

        Instrutor resultado = instrutorService.salvar(dto);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(instrutorRepository).save(any(Instrutor.class));
    }
}
