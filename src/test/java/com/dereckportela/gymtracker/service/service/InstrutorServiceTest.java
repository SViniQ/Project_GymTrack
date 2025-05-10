package com.dereckportela.gymtracker.service.service;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.dto.InstrutorDtoResponse;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.service.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InstrutorServiceTest {

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private InstructorService instrutorService;
    private Instrutor instrutor;
    private InstrutorDto dto;

    @BeforeEach
    void setUp() {
        dto = new InstrutorDto();
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

        instrutor = new Instrutor();
        instrutor.setId(1L);
        instrutor.setNome(dto.getNome());
        instrutor.setMatricula(dto.getMatricula());
        instrutor.setEmail(dto.getEmail());
        instrutor.setCpf(dto.getCpf());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setSexo(dto.getSexo());
        instrutor.setSalario(dto.getSalario());
        instrutor.setEspecialidade(dto.getEspecialidade());
    }

    @Test
    void verificaListaInstrutores() {

        when(instrutorRepository.findAll()).thenReturn(List.of(instrutor));

        List<InstrutorDtoResponse> listInstructor = instrutorService.listar();

        assertNotNull(listInstructor);
        assertEquals(1, listInstructor.size());
        verify(instrutorRepository, times(1)).findAll();

    }

    @Test
    void verificaCadastroInstrutores() {

        when(instrutorRepository.save(any(Instrutor.class))).thenReturn(instrutor);

        Instrutor resultado = instrutorService.salvar(dto);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(instrutorRepository, times(1)).save(any(Instrutor.class));
    }

    @Test
    void verificaAtualizarInstrutores() {
        dto.setNome("Jose");
        when(instrutorRepository.findById(1L)).thenReturn(Optional.of(instrutor));

        instrutor.setNome("Jose");
        when(instrutorRepository.save(any(Instrutor.class))).thenReturn(instrutor);

        Instrutor resultado = instrutorService.atualizar(instrutor.getId(), dto);

        assertEquals("Jose", resultado.getNome());
        verify(instrutorRepository, times(1)).findById(1L);
        verify(instrutorRepository, times(1)).save(any(Instrutor.class));
    }

    @Test
    void verificaRemocaoInstrutorComSucesso() {
        instrutor.setAlunos(Collections.emptyList());

        when(instrutorRepository.findById(1L)).thenReturn(Optional.of(instrutor));

        instrutorService.remover(1L);

        verify(instrutorRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoRemoverInstrutorComAlunos() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        instrutor.setAlunos(List.of(aluno));

        when(instrutorRepository.findById(1L)).thenReturn(Optional.of(instrutor));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> instrutorService.remover(1L)
        );

        assertEquals("Não é possível excluir o instrutor enquanto houver alunos associados.", exception.getMessage());
        verify(instrutorRepository, never()).deleteById(anyLong());
    }
}
