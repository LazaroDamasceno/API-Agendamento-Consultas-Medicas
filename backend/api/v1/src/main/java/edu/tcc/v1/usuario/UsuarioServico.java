package edu.tcc.v1.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServico {
    
    static UsuarioRepositorio repositorio;

    public static ResponseEntity<Usuario> buscarUsuarioPeloCPF(String cpf) {
        return repositorio
                .findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
