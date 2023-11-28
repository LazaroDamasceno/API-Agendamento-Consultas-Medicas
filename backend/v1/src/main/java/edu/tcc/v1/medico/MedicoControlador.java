package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.prontuario.Prontuario;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoControlador {

    ResponseEntity<List<AgendaMedica>> exibirTodasAsAgendasMedicas(String crm);
    ResponseEntity<List<AgendaMedica>> exibirAgendasMedicasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<Void> cadastrarAgendaMedica(String crm, CadastrarAgendaMedicaDTO dto);
    ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String crm);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String crm);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String crm);
    ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoCliente(String crm, String nomeCliente);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadasPeloNomeDoCliente(String crm, String nomeCliente);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladasPeloNomeDoCliente(String crm, String nomeCliente);
    ResponseEntity<List<Consulta>> exibirConsultasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<Void> cadastrarProntuario(String crm, String cpf);
    ResponseEntity<Prontuario> exibirProntuarioPeloCliente(String crm, String cpf);
    ResponseEntity<List<Prontuario>> exibirTodosOsProntuarios(String crm);
    ResponseEntity<List<Prontuario>> exibirProntuariosEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal);
    ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, LocalDateTime dataAgendamento);

}
