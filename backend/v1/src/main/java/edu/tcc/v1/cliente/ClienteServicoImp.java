package edu.tcc.v1.cliente;

import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServicoImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServicoImp implements ClienteServico {

    private ClienteRepository repository;
    private UsuarioServicoImp usuarioServico;

    @Override
    public void cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = new Cliente(dto);
        repository.save(cliente);
    }

    @Override
    public List<Cliente> exibirTodosOsClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente exibirClientePeloCPF(String cpf) {
        Cliente cliente = null;
        Usuario usuario = usuarioServico.exibirUsuarioPorCpf(cpf);
        Optional<Cliente> clienteOptional = repository.findByUsuario(usuario);
        if (clienteOptional.isPresent()) cliente = clienteOptional.get();
        return cliente;
    }

}
