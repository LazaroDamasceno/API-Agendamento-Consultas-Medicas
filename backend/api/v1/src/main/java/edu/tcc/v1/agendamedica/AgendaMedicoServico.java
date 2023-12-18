package edu.tcc.v1.agendamedica;

import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaMedicoServico {

    ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico);
    ResponseEntity<List<AgendaMedica>> buscarAgendasMedicas(Medico medico);
    ResponseEntity<List<AgendaMedica>> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico);

}
