package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaMedicaServico {

    AgendaMedica exibirAgendaMedicaPelaDataDisponivel(LocalDateTime dataDisponivel);
    List<AgendaMedica> exibirTodasAsAgendasMedicas();
    List<AgendaMedica> exibirAgendasMedicasEntreDatas(LocalDateTime dataIncial, LocalDateTime dataFinal);
    void cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto);
    void associarConsulta(LocalDateTime dataDisponivel, Consulta consulta);
    void dessassociarConsulta(LocalDateTime dataDisponivel);

}
