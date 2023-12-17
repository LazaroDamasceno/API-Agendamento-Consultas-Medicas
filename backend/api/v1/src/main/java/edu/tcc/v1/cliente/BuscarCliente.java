package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.BuscarUsuario;
import edu.tcc.v1.usuario.Usuario;
import org.springframework.http.ResponseEntity;

public class BuscarCliente {

    ClienteRepositorio repositorio;

    public ResponseEntity<Cliente> buscar(String cpf) {
        Usuario usuario = new BuscarUsuario().buscarUsuarioPeloCPF(cpf).getBody();
        return repositorio
                .findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
