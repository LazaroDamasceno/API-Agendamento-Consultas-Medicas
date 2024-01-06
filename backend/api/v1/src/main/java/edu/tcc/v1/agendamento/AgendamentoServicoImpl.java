package edu.tcc.v1.agendamento;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.facade.Facade;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoServicoImpl implements AgendamentoServico {

    private AgendamentoRepositorio repositorio;
    private Facade facade;

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendamentoDTO dto, Medico medico) {
        Agendamento agendaMedica = AgendamentoRepositorio.instanciar(dto, medico);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public void associarConsulta(LocalDateTime dataDisponivel, Medico medico, Consulta consulta) {
        Agendamento agendaMedica = facade.buscarAgendamento(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsulta(consulta);
            repositorio.save(agendaMedica);
        }
    }

    @Override
    public void desassociarConsulta(LocalDateTime dataDisponivel, Medico medico) {
        Agendamento agendaMedica = facade.buscarAgendamento(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsulta(null);
            repositorio.save(agendaMedica);
        }
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicas(Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicas(medico), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicasEntreDatas(dataInicial, dataFinal, medico), HttpStatus.OK);
    }

}
