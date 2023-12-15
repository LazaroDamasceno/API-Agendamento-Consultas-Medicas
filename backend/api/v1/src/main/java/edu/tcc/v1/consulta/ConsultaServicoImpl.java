package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultaServicoImpl implements ConsultaServico {

    private static ConsultaRepositorio repositorio;

    public static ResponseEntity<Consulta> buscarConsultaPeloMedico(LocalDateTime dataHora, Medico medico) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                        && e.getDataCancelamento() == null
                        && e.getMedico().equals(medico)
                ).findFirst();
        return consulta.isPresent() ? ResponseEntity.ok(consulta.get()) : ResponseEntity.badRequest().build();
    }

    public static ResponseEntity<Consulta> buscarConsultaPeloCliente(LocalDateTime dataHora, Cliente cliente) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getCliente().equals(cliente)
                ).findFirst();
        return consulta.isPresent() ? ResponseEntity.ok(consulta.get()) : ResponseEntity.badRequest().build();
    }

    @Override
    public void agendarConsulta(AgendarConsultaDTO dto, Cliente cliente, Medico medico) {
        Consulta consulta = ConsultaRepositorio.instanciar(dto, cliente, medico);
        repositorio.save(consulta);
    }

    @Override
    public void cancelarConsulta(LocalDateTime dataAgendamento, Cliente cliente) {
        Consulta consulta = buscarConsultaPeloCliente(dataAgendamento, cliente).getBody();
        consulta.setDataCancelamento(LocalDateTime.now());
        repositorio.save(consulta);
    }

    @Override
    public List<Consulta> buscarTodasAsConsultas() {
        return repositorio.buscarConsultasAgendadas();
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
