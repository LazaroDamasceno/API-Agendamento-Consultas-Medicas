package edu.tcc.v1.consulta;

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
public class ConsultaServicoImpl implements ConsultaServico {

    private ConsultaRepositorio repositorio;
    private AgendamentoServicoImpl agendamentoServico;
    private Facade facade;

    @Override
    public ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, Cliente cliente, Medico medico) {
        Consulta consulta = ConsultaRepositorio.instanciar(dto, cliente, medico);
        repositorio.save(consulta);
        agendamentoServico.associarConsulta(Facade.conversorDataHora(dto.dataAgendamento()), medico, consulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(LocalDateTime dataAgendamento, Cliente cliente) {
        Consulta consulta = facade.buscarConsultaPeloCliente(dataAgendamento, cliente).getBody();
        if (consulta == null) return ResponseEntity.badRequest().build();
        consulta.setDataCancelamento(LocalDateTime.now());
        repositorio.save(consulta);
        agendamentoServico.desassociarConsulta(dataAgendamento, consulta.getMedico());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(Medico medico, LocalDateTime dataAgendamento, String observacoes) {
        Consulta consulta = facade.buscarConsultaPeloMedico(dataAgendamento, medico).getBody();
        if (consulta == null) return ResponseEntity.badRequest().build();
        consulta.setObservacoesMedicas(observacoes);
        repositorio.saveAndFlush(consulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Consulta> buscarConsultas() {
        return repositorio.buscarConsultas();
    }

    @Override
    public List<Consulta> buscarConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> buscarConsultasAgendadas() {
        return repositorio.buscarConsultasAgendadas();
    }

    @Override
    public List<Consulta> buscarConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasAgendadasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> buscarConsultasCanceladas() {
        return repositorio.buscarConsultasCanceladas();
    }

    @Override
    public List<Consulta> buscarConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasCanceladasEntreDatas(dataInicial, dataFinal);
    }

}
