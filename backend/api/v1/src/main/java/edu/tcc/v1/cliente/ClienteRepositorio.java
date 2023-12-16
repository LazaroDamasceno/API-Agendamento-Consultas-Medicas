package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositorio extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByUsuario(Usuario usuario);

    static Cliente instaciar(CadastrarClienteDTO dto) {
        return new Cliente(dto);
    }

}
