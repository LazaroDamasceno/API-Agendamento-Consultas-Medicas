package edu.tcc.v1.constantes;

import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataHora {

    private static final String padrao = "dd-MM-yyyy HH:mm:ss";

    public static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padrao);
    
}
