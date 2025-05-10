package com.dereckportela.gymtracker.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraIMCTeste {
    @Test
    void deveCalcularIMC() {
        double imc = CalculadoraIMC.calcularIMC(70, 1.75);
        assertEquals(22.86, imc, 0.01);
    }

    @Test
    void deveLancarExcecaoDivisaoPorZero(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
           CalculadoraIMC.calcularIMC(70, 0);
        });

        assertEquals("Altura não pode ser zero.", thrown.getMessage());
    }
}
