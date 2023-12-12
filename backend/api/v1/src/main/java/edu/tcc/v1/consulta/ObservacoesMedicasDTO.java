package edu.tcc.v1.consulta;

import jakarta.validation.constraints.NotBlank;

public record ObservacoesMedicasDTO(
    @NotBlank String observacoes
) {}
