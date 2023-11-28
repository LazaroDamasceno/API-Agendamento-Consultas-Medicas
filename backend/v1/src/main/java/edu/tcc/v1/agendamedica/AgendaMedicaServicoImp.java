package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaMedicaServicoImp implements AgendaMedicaServico {

    private AgendaMedicaRepositorio repositorio;

    @Override
    public AgendaMedica exibirAgendaMedicaPelaDataDisponivel(LocalDateTime dataDisponivel) {
        AgendaMedica am = null;
        Optional<AgendaMedica> amOptional = repositorio.findByDataDisponivel(dataDisponivel);
        if (amOptional.isPresent()) am = amOptional.get();
        return am;
    }

    @Override
    public List<AgendaMedica> exibirTodasAsAgendasMedicas() {
        return repositorio.findAll();
    }

    @Override
    public List<AgendaMedica> exibirAgendasMedicasEntreDatas(LocalDateTime dataIncial, LocalDateTime dataFinal) {
        return repositorio.exibirAgendasMedicasEntreDatas(dataIncial, dataFinal);
    }

    @Override
    public void cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto) {
        AgendaMedica am = new AgendaMedica(dto);
        repositorio.save(am);
    }

    @Override
    public void associarConsulta(LocalDateTime dataDisponivel, Consulta consulta) {
        AgendaMedica am = exibirAgendaMedicaPelaDataDisponivel(dataDisponivel);
        am.setConsulta(consulta);
        repositorio.saveAndFlush(am);
    }

    @Override
    public void desassociarConsulta(LocalDateTime dataDisponivel) {
        AgendaMedica am = exibirAgendaMedicaPelaDataDisponivel(dataDisponivel);
        am.setConsulta(null);
        repositorio.saveAndFlush(am);
    }

    @Override
    public void associarMedico(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica am = exibirAgendaMedicaPelaDataDisponivel(dataDisponivel);
        am.setMedico(medico);
        repositorio.saveAndFlush(am);
    }

}
