package edu.tcc.v1.agendamedica;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CadastrarAgendaMedicaDTO(
        @NotBlank LocalDateTime dataDisponivel
) {
}
