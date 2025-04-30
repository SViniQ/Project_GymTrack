package com.dereckportela.gymtracker.service;

import com.dereckportela.gymtracker.dto.AlunoDto;
import com.dereckportela.gymtracker.exception.RecursoNaoEncontradoException;
import com.dereckportela.gymtracker.model.Aluno;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.dereckportela.gymtracker.service.AlunoService;
import com.dereckportela.gymtracker.service.InstructorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveLancarExcecaoQuandoInstrutorNaoForEncontrado() {

        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setInstrutorId(1L);

        when(instrutorRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RecursoNaoEncontradoException exception = assertThrows(RecursoNaoEncontradoException.class, () -> {
            alunoService.cadastrar(alunoDto);
        });

        assertEquals("Instrutor nao encontrado", exception.getMessage());
        verify(instrutorRepository).findById(1L);
    }
}
