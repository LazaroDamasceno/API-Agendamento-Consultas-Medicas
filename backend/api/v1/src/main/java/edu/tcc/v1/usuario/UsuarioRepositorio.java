package edu.tcc.v1.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {

    @Query("""
        select u from Usuario u
        where u.cpf = :cpf   
    """)
    Usuario buscarUsuario(@Param("cpf") String cpf);

    static Usuario instanciar(CadastrarUsuarioDTO dto) {
        return new Usuario(dto);
    }
    
}
