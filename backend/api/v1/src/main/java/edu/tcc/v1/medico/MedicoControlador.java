package edu.tcc.v1.medico;

import edu.tcc.v1.agendamento.Agendamento;
import edu.tcc.v1.agendamento.CadastrarAgendamentoDTO;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.consultamedica.ObservacoesMedicasDTO;
import edu.tcc.v1.prontuario.Prontuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicoControlador {

    ResponseEntity<Void> cadastrarAgendamento(CadastrarAgendamentoDTO dto, String crm);
    ResponseEntity<List<Agendamento>> buscarAgendamentos(String crm);
    ResponseEntity<List<Agendamento>> buscarAgendamentosEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(String crm,String dataAgendamento, ObservacoesMedicasDTO dto);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(String crm);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(String crm);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(String crm);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoCliente(String crm, String nome);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoCliente(String crm, String nome);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoCliente(String crm, String nome);
    ResponseEntity<Void> criarProntuario(String crm, String cpf);
    ResponseEntity<Void> adicionarConsultaMedicaAoProntuario(String crm, String cpf, String dataAgendamento);
    ResponseEntity<Prontuario> buscarProntuarioDeCliente(String crm, String cpf);
    ResponseEntity<List<Prontuario>> buscarProntuarios(String crm);   


}
