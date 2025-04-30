package com.dereckportela.gymtracker.unit.service;

import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.dto.AlunoDtoResponse;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.AlunoRepository;
import com.dereckportela.gymtracker.repository.InstrutorRepository;

import com.dereckportela.gymtracker.unit.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private InstrutorRepository instrutorRepository;
    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;
    private AlunoDto alunoDto;
    private Aluno aluno;
    private Instrutor instrutor;

    @BeforeEach
    void setUp() {
        alunoDto = new AlunoDto();
        alunoDto.setMatricula("A123");
        alunoDto.setNome("Carlos");
        alunoDto.setEmail("carlos@example.com");
        alunoDto.setTelefone("11999999999");
        alunoDto.setEndereco("Rua Exemplo, 123");
        alunoDto.setCpf("12345678901");
        alunoDto.setSexo("M");
        alunoDto.setPeso(75.5);
        alunoDto.setAltura(1.80);
        alunoDto.setIdade(25);
        alunoDto.setObjetivo("Perder peso");
        alunoDto.setInstrutorId(1L);

        instrutor = new Instrutor();
        instrutor.setId(1L);
        instrutor.setNome("Instrutor João");

        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setMatricula(alunoDto.getMatricula());
        aluno.setNome(alunoDto.getNome());
        aluno.setEmail(alunoDto.getEmail());
        aluno.setTelefone(alunoDto.getTelefone());
        aluno.setCpf(alunoDto.getCpf());
        aluno.setSexo(alunoDto.getSexo());
        aluno.setPeso(alunoDto.getPeso());
        aluno.setAltura(alunoDto.getAltura());
        aluno.setIdade(alunoDto.getIdade());
        aluno.setObjetivo(alunoDto.getObjetivo());
        aluno.setInstrutor(instrutor);

    }

    @Test
    void deveListarAlunos() {
        when(alunoRepository.findAll()).thenReturn(List.of(aluno));
        List<AlunoDtoResponse> response = alunoService.listar();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        Aluno alunoCadastrado = alunoRepository.save(aluno);

        assertNotNull(alunoCadastrado);
        assertEquals("Carlos", alunoCadastrado.getNome());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void deveLancarExcecaoQuandoInstrutorNaoForEncontrado() {
        when(instrutorRepository.findById(1L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException exception = assertThrows(RecursoNaoEncontradoException.class, () -> {
            alunoService.cadastrar(alunoDto);
        });

        assertEquals("Instrutor nao encontrado", exception.getMessage());
        verify(instrutorRepository).findById(1L);
    }

    @Test
    void deveAtualizarAlunoComSucesso() {
        alunoDto.setNome("Mauricio");
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(instrutorRepository.findById(1L)).thenReturn(Optional.of(instrutor));

        aluno.setNome("Mauricio");
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno alunoAlterado = alunoService.atualizar(aluno.getId(), alunoDto);

        assertNotNull(alunoAlterado);
        assertEquals("Mauricio", alunoAlterado.getNome());
        verify(instrutorRepository, times(1)).findById(1L);
        verify(alunoRepository, times(1)).findById(1L);
        verify(alunoRepository, times(1)).save(aluno);

    }

    @Test
    void deveExcluirAlunoComSucesso() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        alunoService.remover(1L);

        verify(alunoRepository, times(1)).delete(aluno);
    }
}
