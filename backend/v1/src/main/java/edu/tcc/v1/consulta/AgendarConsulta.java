package edu.tcc.v1.consulta;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AgendarConsulta(
        @NotBlank
        LocalDateTime dataAgendamento
) {
}
