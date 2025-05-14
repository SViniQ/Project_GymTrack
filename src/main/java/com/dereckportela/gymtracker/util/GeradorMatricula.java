package com.dereckportela.gymtracker.util;

import java.util.UUID;

public class GeradorMatricula {
    public static String gerarMatricula() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
