package edu.tcc.v1.usuario;

import org.springframework.http.ResponseEntity;

public class BuscarUsuarioImpl {

    UsuarioRepositorio repositorio;

    public ResponseEntity<Usuario> buscarUsuarioPeloCPF(String cpf) {
        return repositorio
                .findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
