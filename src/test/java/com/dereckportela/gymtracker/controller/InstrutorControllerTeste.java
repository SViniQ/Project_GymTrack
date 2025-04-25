package com.dereckportela.gymtracker.controller;

import com.dereckportela.gymtracker.dto.InstrutorDto;
import com.dereckportela.gymtracker.model.Instrutor;
import com.dereckportela.gymtracker.repository.InstrutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;

@WebMvcTest(InstrutorController.class)
public class InstrutorControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InstrutorRepository instrutorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrar() throws Exception {
        InstrutorDto instrutorDto = new InstrutorDto();
        instrutorDto.setNome("Dereck");
        instrutorDto.setCpf("99999999999");
        instrutorDto.setIdade(31);
        instrutorDto.setMatricula("123");
        instrutorDto.setEmail("dereckportela@gmail.com");

        Instrutor instrutor = new Instrutor();
        instrutor.setId(1L);
        instrutor.setNome(instrutorDto.getNome());
        instrutor.setCpf(instrutorDto.getCpf());
        instrutor.setIdade(instrutorDto.getIdade());
        instrutor.setMatricula(instrutorDto.getMatricula());
        instrutor.setEmail(instrutorDto.getEmail());

        when(instrutorRepository.save(instrutor)).thenReturn(instrutor);

        mockMvc.perform(post("/instrutor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instrutorDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
