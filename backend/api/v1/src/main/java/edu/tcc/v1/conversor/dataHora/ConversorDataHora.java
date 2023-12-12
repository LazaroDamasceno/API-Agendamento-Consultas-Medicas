package edu.tcc.v1.conversor.dataHora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.tcc.v1.constantes.DataHora;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversorDataHora {

    private static final DateTimeFormatter conversorDataHora = DateTimeFormatter.ofPattern(DataHora.PADRAO);

    public static LocalDateTime conversor(String dataHora) {
        return LocalDateTime.parse(dataHora, conversorDataHora);
    }

}
