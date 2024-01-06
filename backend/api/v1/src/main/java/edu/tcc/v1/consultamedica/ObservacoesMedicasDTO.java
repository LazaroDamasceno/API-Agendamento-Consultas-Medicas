package edu.tcc.v1.consultamedica;

import jakarta.validation.constraints.NotBlank;

public record ObservacoesMedicasDTO(
    @NotBlank String observacoes
) {}
