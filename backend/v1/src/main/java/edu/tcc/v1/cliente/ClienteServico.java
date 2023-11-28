package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsulta;
import edu.tcc.v1.consulta.Consulta;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ClienteServico {

    void cadastrarCliente(CadastrarClienteDTO dto);
    List<Cliente> exibirTodosOsClientes();
    Cliente exibirClientePeloCPF(String cpf);

    ResponseEntity<Void> cadastrarConsulta(String cpf, AgendarConsulta dto);
    ResponseEntity<Void> cancelarConsulta(String cpf, LocalDateTime dataAgendamento);
    ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String cpf);
    ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(String cpf, String nomeMedico);

}
