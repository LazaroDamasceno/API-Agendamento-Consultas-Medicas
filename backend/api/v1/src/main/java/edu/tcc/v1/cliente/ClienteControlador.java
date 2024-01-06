package edu.tcc.v1.cliente;

import edu.tcc.v1.consultamedica.AgendarConsultaMedicaDTO;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteControlador {

    ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto);
    ResponseEntity<Void> agendarConsultaMedica(AgendarConsultaMedicaDTO dto, String crm, String cpf);
    ResponseEntity<Void> cancelarConsultaMedica(String cpf, String dataAgendamento);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(String cpf);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(String cpf);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(String cpf);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoMedico(String cpf, String nome);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoMedico(String cpf, String nome);
    ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoMedico(String cpf, String nome);

}
