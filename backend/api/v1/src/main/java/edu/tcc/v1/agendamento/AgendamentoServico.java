package edu.tcc.v1.agendamento;

import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoServico {

    ResponseEntity<Void> cadastrarAgendamento(CadastrarAgendamentoDTO dto, Medico medico);
    void associarConsultaMedica(LocalDateTime dataDisponivel, Medico medico, ConsultaMedica consulta);
    void desassociarConsultaMedica(LocalDateTime dataDisponivel, Medico medico);
    ResponseEntity<List<Agendamento>> buscarAgendamentos(Medico medico);
    ResponseEntity<List<Agendamento>> buscarAgendamentosEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico);

}
