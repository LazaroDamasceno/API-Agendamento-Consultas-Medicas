package edu.tcc.v1.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {

    static Usuario instanciar(CadastrarUsuarioDTO dto) {
        return new Usuario(dto);
    }
    
}
