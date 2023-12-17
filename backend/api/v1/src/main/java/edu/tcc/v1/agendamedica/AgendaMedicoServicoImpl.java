package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendaMedicoServicoImpl implements AgendaMedicoServico {

    private AgendaMedicaRepositorio repositorio;

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico) {
        AgendaMedica agendaMedica = AgendaMedicaRepositorio.instanciar(dto, medico);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta) {
        AgendaMedica agendaMedica = new BuscarAgendaMedica().buscar(dataDisponivel, medico).getBody();
        if (agendaMedica == null) return ResponseEntity.badRequest().build();
        agendaMedica.setConsulta(consulta);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> desassociarConsulta(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica agendaMedica = new BuscarAgendaMedica().buscar(dataDisponivel, medico).getBody();
        if (agendaMedica == null) return ResponseEntity.badRequest().build();
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
