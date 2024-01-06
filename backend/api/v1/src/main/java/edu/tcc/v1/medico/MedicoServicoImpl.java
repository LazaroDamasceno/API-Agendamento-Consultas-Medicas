package edu.tcc.v1.medico;

import edu.tcc.v1.agendamento.*;
import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.consulta.ObservacoesMedicasDTO;
import edu.tcc.v1.facade.Facade;
import edu.tcc.v1.prontuario.Prontuario;
import edu.tcc.v1.prontuario.ProntuarioServicoImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicoServicoImpl implements MedicoServico {

    private MedicoRepositorio repositorio;
    private AgendamentoServicoImpl agendamentoServico;
    private ConsultaServicoImpl consultaServico;
    private ProntuarioServicoImpl prontuarioServico;
    private Facade facade;

    @Override
    public ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto) {
        Medico medico = new Medico(dto);
        repositorio.save(medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> demitirMedico(Medico medico) {
        medico.setDataDemissao(LocalDateTime.now());
        repositorio.saveAndFlush(medico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Medico>> buscarMedicos() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(CadastrarAgendamentoDTO dto, String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        agendamentoServico.cadastrarAgendaMedica(dto, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        return agendamentoServico.buscarAgendasMedicas(medico);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        return agendamentoServico.buscarAgendasMedicasEntreDatas(di, df, medico);
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(String crm, String dataAgendamento, ObservacoesMedicasDTO dto) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime da = Facade.conversorDataHora(dataAgendamento);
        consultaServico.adicionarObservacoesMedicasAConsulta(medico, da, dto.observacoes());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                        && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()) 
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoCliente(String crm, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoCliente(String crm, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoCliente(String crm, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<Void> criarProntuario(String crm, String cpf) {
        Medico medico = facade.buscarMedico(crm).getBody();
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        prontuarioServico.criarProntuario(medico, cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, String dataAgendamento) {
        Medico medico = facade.buscarMedico(crm).getBody();
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime da = Facade.conversorDataHora(dataAgendamento);
        Consulta consulta = facade.buscarConsultaPeloMedico(da, medico).getBody();
        prontuarioServico.adicionarConsultaAoProntuario(medico, cliente, consulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Prontuario> buscarProntuarioDeCliente(String crm, String cpf) {
        Medico medico = facade.buscarMedico(crm).getBody();
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        return facade.buscarProntuarioPorCliente(medico, cliente);
    }

    @Override
    public ResponseEntity<List<Prontuario>> buscarProntuarios(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        return prontuarioServico.buscarProntuariosPorMedico(medico);
    }

}
