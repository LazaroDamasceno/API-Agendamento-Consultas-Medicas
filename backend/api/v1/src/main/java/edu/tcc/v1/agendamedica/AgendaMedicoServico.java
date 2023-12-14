package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaMedicoServico {

    void cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico);
    void associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta);
    void desassociarConsulta(LocalDateTime dataDisponivel, Medico medico);
    List<AgendaMedica> buscarTodasAsAgendasMedicas(Medico medico);
    List<AgendaMedica> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico);

}
