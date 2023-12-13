package edu.tcc.v1.auxiliares;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversorDataHora {

    private static final String PADRAO_HORA = "dd-MM-yyyy";
    private static final String PADRAO_DATA_HORA = "dd-MM-yyyy HH:mm:ss";
    private static final DateTimeFormatter conversorData = DateTimeFormatter.ofPattern(PADRAO_HORA);
    private static final DateTimeFormatter conversorDataHora = DateTimeFormatter.ofPattern(PADRAO_DATA_HORA);

    public static LocalDateTime conversorData(String dataHora) {
        return LocalDateTime.parse(dataHora, conversorDataHora);
    }


    public static LocalDate conversorDataHora(String dataHora) {
        return LocalDate.parse(dataHora, conversorData);
    }

}
