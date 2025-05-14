package com.dereckportela.gymtracker.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeradorMatriculaTeste {
    @Test
    void deveGerarMatriculaComoString(){
        String matricula = GeradorMatricula.gerarMatricula();

        assertNotNull(matricula);
        assertEquals(8, matricula.length());
    }


}
