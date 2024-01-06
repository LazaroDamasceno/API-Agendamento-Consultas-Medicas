package edu.tcc.v1.agendamento;

import edu.tcc.v1.consultamedica.ConsultaMedica;
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
    public ResponseEntity<Void> cadastrarAgendamento(CadastrarAgendamentoDTO dto, Medico medico) {
        Agendamento agendaMedica = AgendamentoRepositorio.instanciar(dto, medico);
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public void associarConsultaMedica(LocalDateTime dataDisponivel, Medico medico, ConsultaMedica consulta) {
        Agendamento agendaMedica = facade.buscarAgendamento(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsultaMedica(consulta);
            repositorio.save(agendaMedica);
        }
    }

    @Override
    public void desassociarConsultaMedica(LocalDateTime dataDisponivel, Medico medico) {
        Agendamento agendaMedica = facade.buscarAgendamento(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsultaMedica(null);
            repositorio.save(agendaMedica);
        }
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendamentos(Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicas(medico), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendamentosEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal, Medico medico) {
        return new ResponseEntity<>(repositorio.buscarAgendasMedicasEntreDatas(dataInicial, dataFinal, medico), HttpStatus.OK);
    }

}
