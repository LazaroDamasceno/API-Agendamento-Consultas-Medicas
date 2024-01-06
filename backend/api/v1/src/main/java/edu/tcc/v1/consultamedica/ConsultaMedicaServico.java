package edu.tcc.v1.consultamedica;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaMedicaServico {

    ResponseEntity<Void> agendarConsultaMedica(AgendarConsultaMedicaDTO dto, Cliente cliente, Medico medico);
    ResponseEntity<Void> cancelarConsultaMedica(LocalDateTime dataAgendamento, Cliente cliente);
    ResponseEntity<Void> adicionarObservacoesMedicasAConsultaMedica(Medico medico, LocalDateTime dataAgendamento, String observacoes);
    List<ConsultaMedica> buscarConsultasMedicas();
    List<ConsultaMedica> buscarConsultasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<ConsultaMedica> buscarConsultasMedicasAgendadas();
    List<ConsultaMedica> buscarConsultasMedicasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<ConsultaMedica> buscarConsultasMedicasCanceladas();
    List<ConsultaMedica> buscarConsultasMedicasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
