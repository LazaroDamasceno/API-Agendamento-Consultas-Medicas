package edu.tcc.v1.medico;

import edu.tcc.v1.usuario.CadastrarUsuarioDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarMedicoDTO(
        @NotBlank
        String crm,
        @NotBlank
        String cnpj,
        @NotNull
        CadastrarUsuarioDTO usuario
) {
}
