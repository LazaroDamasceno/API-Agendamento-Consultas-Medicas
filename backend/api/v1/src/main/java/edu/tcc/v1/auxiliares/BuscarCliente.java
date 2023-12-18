package edu.tcc.v1.auxiliares;


import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.cliente.ClienteRepositorio;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuscarCliente {

    private final ClienteRepositorio repositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public ResponseEntity<Cliente> buscar(String cpf) {
        Usuario usuario = new BuscarUsuario(usuarioRepositorio).buscarUsuarioPeloCPF(cpf).getBody();
        return repositorio
                .findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
