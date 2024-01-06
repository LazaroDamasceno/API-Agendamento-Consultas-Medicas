package edu.tcc.v1.agendamento;

import jakarta.validation.constraints.NotBlank;

public record CadastrarAgendamentoDTO(
        @NotBlank String dataDisponivel
) {
}
