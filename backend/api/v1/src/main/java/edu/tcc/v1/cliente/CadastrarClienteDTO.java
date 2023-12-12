package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.CadastrarUsuarioDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarClienteDTO(
        @NotBlank
        String cep,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotNull
        CadastrarUsuarioDTO usuario
) {
}
