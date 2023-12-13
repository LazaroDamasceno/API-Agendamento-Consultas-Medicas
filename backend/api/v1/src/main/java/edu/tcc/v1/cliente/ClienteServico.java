package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteServico {

    List<Cliente> exibirTodosOsClientes();
    Cliente exibirClientePeloCPF(String cpf);
    ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto);
    ResponseEntity<Void> agendarConsulta(String cpf, AgendarConsultaDTO dto);
    ResponseEntity<Void> cancelarConsulta(String cpf, String dataAgendamento);
    ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(String cpf, String nomeMedico);

}
