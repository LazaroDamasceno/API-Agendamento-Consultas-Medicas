package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaMedicoServicoImpl implements AgendaMedicoServico {

    private static AgendaMedicaRepositorio repositorio;

    public static ResponseEntity<AgendaMedica> buscarAgendaMedica(LocalDateTime dataDisponivel, Medico medico) {
        Optional<AgendaMedica> agendaMedica = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataDisponivel().equals(dataDisponivel)
                        && e.getMedico().equals(medico)
                ).findFirst();
        return agendaMedica.isPresent() ? ResponseEntity.ok(agendaMedica.get()) : ResponseEntity.badRequest().build();
    }

    @Override
    public void cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico) {
        AgendaMedica agendaMedica = AgendaMedicaRepositorio.instanciar(dto);
        agendaMedica.setMedico(medico);
        repositorio.save(agendaMedica);
    }

    @Override
    public void associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta) {
        AgendaMedica agendaMedica = buscarAgendaMedica(dataDisponivel, medico).getBody();
        agendaMedica.setConsulta(consulta);
        repositorio.save(agendaMedica);
    }

    @Override
    public void desassociarConsulta(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica agendaMedica = buscarAgendaMedica(dataDisponivel, medico).getBody();
        agendaMedica.setConsulta(null);
        repositorio.save(agendaMedica);
    }

    @Override
    public List<AgendaMedica> buscarTodasAsAgendasMedicas(Medico medico) {
        return repositorio.buscarTodasAsAgendasMedicas(medico);
    }

    @Override
    public List<AgendaMedica> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico) {
        return repositorio.buscarAgendasMedicasEntreDatas(dataInicial, dataFinal, medico);
    }

}
