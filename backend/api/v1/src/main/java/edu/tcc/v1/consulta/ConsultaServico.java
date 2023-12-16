package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaServico {
    
    void agendarConsulta(AgendarConsultaDTO dto, Cliente cliente, Medico medico);
    void cancelarConsulta(LocalDateTime dataAgendamento, Cliente cliente);
    List<Consulta> buscarConsultas();
    List<Consulta> buscarConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> buscarConsultasAgendadas();
    List<Consulta> buscarConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> buscarConsultasCanceladas();
    List<Consulta> buscarConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
