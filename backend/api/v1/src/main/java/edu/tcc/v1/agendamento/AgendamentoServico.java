package edu.tcc.v1.agendamento;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoServico {

    ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendamentoDTO dto, Medico medico);
    void associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta);
    void desassociarConsulta(LocalDateTime dataDisponivel, Medico medico);
    ResponseEntity<List<Agendamento>> buscarAgendasMedicas(Medico medico);
    ResponseEntity<List<Agendamento>> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico);

}
