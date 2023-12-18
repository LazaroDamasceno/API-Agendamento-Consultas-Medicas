package edu.tcc.v1.auxiliares;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioRepositorio;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscarUsuario {

    private final UsuarioRepositorio repositorio;

    public ResponseEntity<Usuario> buscarUsuarioPeloCPF(String cpf) {
        return repositorio
                .findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
