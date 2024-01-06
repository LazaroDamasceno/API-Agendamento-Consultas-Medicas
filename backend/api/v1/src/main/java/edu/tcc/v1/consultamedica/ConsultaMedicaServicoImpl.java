package edu.tcc.v1.consultamedica;

import edu.tcc.v1.agendamento.AgendamentoServicoImpl;
import edu.tcc.v1.cliente.Cliente;
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
public class ConsultaMedicaServicoImpl implements ConsultaMedicaServico {

    private ConsultaMedicaRepositorio repositorio;
    private AgendamentoServicoImpl agendamentoServico;
    private Facade facade;

    @Override
    public ResponseEntity<Void> agendarConsultaMedica(AgendarConsultaMedicaDTO dto, Cliente cliente, Medico medico) {
        ConsultaMedica consulta = ConsultaMedicaRepositorio.instanciar(dto, cliente, medico);
        repositorio.save(consulta);
        agendamentoServico.associarConsultaMedica(Facade.conversorDataHora(dto.dataAgendamento()), medico, consulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsultaMedica(LocalDateTime dataAgendamento, Cliente cliente) {
        ConsultaMedica consulta = facade.buscarConsultaPeloCliente(dataAgendamento, cliente).getBody();
        if (consulta == null) return ResponseEntity.badRequest().build();
        consulta.setDataCancelamento(LocalDateTime.now());
        repositorio.save(consulta);
        agendamentoServico.desassociarConsultaMedica(dataAgendamento, consulta.getMedico());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsultaMedica(Medico medico, LocalDateTime dataAgendamento, String observacoes) {
        ConsultaMedica consulta = facade.buscarConsultaPeloMedico(dataAgendamento, medico).getBody();
        if (consulta == null) return ResponseEntity.badRequest().build();
        consulta.setObservacoesMedicas(observacoes);
        repositorio.saveAndFlush(consulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicas() {
        return repositorio.buscarConsultasMedicas();
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasMedicasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicasAgendadas() {
        return repositorio.buscarConsultasMedicasAgendadas();
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasMedicasAgendadasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicasCanceladas() {
        return repositorio.buscarConsultasMedicasCanceladas();
    }

    @Override
    public List<ConsultaMedica> buscarConsultasMedicasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasMedicasCanceladasEntreDatas(dataInicial, dataFinal);
    }

}
