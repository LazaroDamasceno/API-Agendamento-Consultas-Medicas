package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByUsuario(Usuario usuario);

}
