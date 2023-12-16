package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaMedicoServicoImpl implements AgendaMedicoServico {

    static AgendaMedicaRepositorio repositorio;

    public static ResponseEntity<AgendaMedica> buscarAgendaMedica(LocalDateTime dataDisponivel, Medico medico) {
        Optional<AgendaMedica> agendaMedica = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataDisponivel().equals(dataDisponivel)
                        && e.getMedico().equals(medico)
                ).findFirst();
        return agendaMedica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico) {
        AgendaMedica agendaMedica = AgendaMedicaRepositorio.instanciar(dto, medico);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta) {
        AgendaMedica agendaMedica = buscarAgendaMedica(dataDisponivel, medico).getBody();
        agendaMedica.setConsulta(consulta);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> desassociarConsulta(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica agendaMedica = buscarAgendaMedica(dataDisponivel, medico).getBody();
        agendaMedica.setConsulta(null);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> buscarAgendasMedicas(Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicas(medico), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicasEntreDatas(dataInicial, dataFinal, medico), HttpStatus.OK);
    }

}
