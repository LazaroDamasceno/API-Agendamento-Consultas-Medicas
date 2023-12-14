package edu.tcc.v1.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class UsuarioServico {
    
    private static UsuarioRepositorio repositorio;

    public static ResponseEntity<Usuario> buscarUsuarioPeloCPF(String crm) {
        Optional<Usuario> usuario = repositorio
            .findAll()
            .stream()
            .filter(e -> e.getCpf().equals(crm))
            .findFirst();
        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.badRequest().build();
    }

}
