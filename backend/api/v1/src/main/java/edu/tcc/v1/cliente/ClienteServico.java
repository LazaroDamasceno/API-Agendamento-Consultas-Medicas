package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteServico {

    ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto);
    ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, String crm, String cpf);
    ResponseEntity<List<Consulta>> buscarConsultas(String cpf);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadas(String cpf);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladas(String cpf);
    ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal);
    ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(String cpf, String crm);
    ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(String cpf, String crm);
    ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(String cpf, String crm);

}
