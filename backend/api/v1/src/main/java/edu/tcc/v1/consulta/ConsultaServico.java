package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaServico {

    ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, Cliente cliente, Medico medico);
    ResponseEntity<Void> cancelarConsulta(LocalDateTime dataAgendamento, Cliente cliente);
    ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(Medico medico, LocalDateTime dataAgendamento, String observacoes);
    List<Consulta> buscarConsultas();
    List<Consulta> buscarConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> buscarConsultasAgendadas();
    List<Consulta> buscarConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> buscarConsultasCanceladas();
    List<Consulta> buscarConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
