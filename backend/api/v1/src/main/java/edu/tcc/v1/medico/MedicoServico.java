package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.prontuario.Prontuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicoServico {

    ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, String crm);
    ResponseEntity<List<AgendaMedica>> buscarAgendasMedicas(String crm);
    ResponseEntity<List<AgendaMedica>> buscarAgendasMedicasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(String crm,String dataAgendamento, String observacoes);
    ResponseEntity<List<Consulta>> buscarConsultas(String crm);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadas(String crm);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladas(String crm);
    ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(String crm, String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(String crm, String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(String crm, String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(String crm, String cpf);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(String crm, String cpf);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(String crm, String cpf);
    ResponseEntity<Void> criarProntuario(String crm, String cpf);
    ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, String dataAgendamento);
    ResponseEntity<Prontuario> buscarProntuarioDeCliente(String crm, String cpf);


}
