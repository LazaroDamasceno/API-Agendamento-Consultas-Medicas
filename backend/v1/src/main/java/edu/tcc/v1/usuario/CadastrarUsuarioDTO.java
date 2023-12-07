package edu.tcc.v1.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastrarUsuarioDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 11, max = 12, message = "O CPF tem 11 dígitos.")
        String cpf,

        @NotBlank
        LocalDate dataNascimento,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 11, max = 12, message = "O telefone tem 11 dígitos.")
        String telefone,

        @NotBlank
        String genero
) {
}
