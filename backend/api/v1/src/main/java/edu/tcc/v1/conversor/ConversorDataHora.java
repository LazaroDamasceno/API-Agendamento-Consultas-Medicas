package edu.tcc.v1.conversor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversorDataHora {

    public static LocalDateTime conversorDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

}
