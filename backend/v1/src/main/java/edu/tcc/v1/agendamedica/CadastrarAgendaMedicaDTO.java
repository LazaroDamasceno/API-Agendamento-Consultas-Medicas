package edu.tcc.v1.agendamedica;

import jakarta.validation.constraints.NotBlank;

public record CadastrarAgendaMedicaDTO(
        @NotBlank String dataDisponivel
) {
}
