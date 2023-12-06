package edu.tcc.v1.agendamedica;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public record CadastrarAgendaMedicaDTO(
        @NotBlank 
        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataDisponivel
) {
}
