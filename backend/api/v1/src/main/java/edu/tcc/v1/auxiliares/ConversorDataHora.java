package edu.tcc.v1.auxiliares;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversorDataHora {

    private static final DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dataHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static LocalDateTime conversorDataHora(String input) {
        return LocalDateTime.parse(input, data);
    }


    public static LocalDate conversorData(String input) {
        return LocalDate.parse(input, dataHora);
    }

}
