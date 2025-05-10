package com.dereckportela.gymtracker.util;

public class CalculadoraIMC {
    public static double calcularIMC(double peso, double altura) {
        if(altura == 0){
            throw new IllegalArgumentException("Altura não pode ser zero.");
        }
        double imc = peso/(altura*altura);
        return Math.round(imc * 100.0) / 100.0;
    }
}
