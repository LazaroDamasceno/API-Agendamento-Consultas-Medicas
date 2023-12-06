package edu.tcc.v1.consulta;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public record AgendarConsultaDTO(
        @NotBlank
        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataAgendamento
) {
}
