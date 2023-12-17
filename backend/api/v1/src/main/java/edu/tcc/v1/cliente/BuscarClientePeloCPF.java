package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.BuscarUsuarioImpl;
import edu.tcc.v1.usuario.Usuario;
import org.springframework.http.ResponseEntity;

public class BuscarClientePeloCPF {

    ClienteRepositorio repositorio;

    public ResponseEntity<Cliente> buscar(String cpf) {
        Usuario usuario = new BuscarUsuarioImpl().buscarUsuarioPeloCPF(cpf).getBody();
        return repositorio
                .findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
