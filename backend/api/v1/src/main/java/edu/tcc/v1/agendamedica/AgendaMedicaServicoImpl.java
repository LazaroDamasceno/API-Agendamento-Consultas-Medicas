package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.auxiliares.AuxiliaresFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendaMedicaServicoImpl implements AgendaMedicaServico {

    private AgendaMedicaRepositorio repositorio;
    private AuxiliaresFacade auxliaresFacade;

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendaMedicaDTO dto, Medico medico) {
        AgendaMedica agendaMedica = AgendaMedicaRepositorio.instanciar(dto, medico);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public void associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta) {
        AgendaMedica agendaMedica = auxliaresFacade.getAgendaMedica().buscar(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsulta(consulta);
            repositorio.save(agendaMedica);
        }
    }

    @Override
    public void desassociarConsulta(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica agendaMedica = auxliaresFacade.getAgendaMedica().buscar(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsulta(null);
            repositorio.save(agendaMedica);
        }
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
