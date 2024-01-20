package edu.tcc.v1.agendamento;

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

    @Override
    public ResponseEntity<Void> cadastrarAgendamento(CadastrarAgendamentoDTO dto, Medico medico) {
        Agendamento agendaMedica = AgendamentoRepositorio.instanciar(dto, medico);
        if (agendaMedica == null) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(agendaMedica);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
