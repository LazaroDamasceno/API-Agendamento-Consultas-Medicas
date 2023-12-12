package edu.tcc.v1.conversor.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.tcc.v1.constantes.Data;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversorData {

    private static final DateTimeFormatter conversorData = DateTimeFormatter.ofPattern(Data.PADRAO);

    public static LocalDate conversor(String dataHora) {
        return LocalDate.parse(dataHora, conversorData);
    }
    
}
