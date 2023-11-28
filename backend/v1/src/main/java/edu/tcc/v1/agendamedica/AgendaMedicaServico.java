package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaMedicaServico {

    AgendaMedica exibirAgendaMedicaPelaDataDisponivel(LocalDateTime dataDisponivel);
    List<AgendaMedica> exibirTodasAsAgendasMedicas();
    List<AgendaMedica> exibirAgendasMedicasEntreDatas(LocalDateTime dataIncial, LocalDateTime dataFinal);
    void cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto);
    void associarConsulta(LocalDateTime dataDisponivel, Consulta consulta);
    void desassociarConsulta(LocalDateTime dataDisponivel);
    void associarMedico(LocalDateTime dataDisponivel, Medico medico);

}
