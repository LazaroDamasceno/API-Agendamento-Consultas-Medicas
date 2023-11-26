package edu.tcc.v1.usuario;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicoImp implements UsuarioServico {

    private UsuarioRepositorio repositorio;

    @Override
    public Usuario exibirUsuarioPorCpf(String cpf) {
        Usuario usuario = null;
        Optional<Usuario> usuarioOptional = repositorio.findByCpf(cpf);
        if (usuarioOptional.isPresent()) usuario = usuarioOptional.get();
        return usuario;
    }

}
