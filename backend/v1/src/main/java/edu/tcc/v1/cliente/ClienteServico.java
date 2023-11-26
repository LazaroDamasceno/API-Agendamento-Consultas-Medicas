package edu.tcc.v1.cliente;

import java.util.List;

public interface ClienteServico {

    void cadastrarCliente(CadastrarClienteDTO dto);
    List<Cliente> exibirTodosOsClientes();
    Cliente exibirClientePeloCPF(String cpf);

}
