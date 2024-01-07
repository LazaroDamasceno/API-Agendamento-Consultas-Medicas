package edu.tcc.v1.medico;

import edu.tcc.v1.agendamento.*;
import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.consultamedica.ConsultaMedicaServicoImpl;
import edu.tcc.v1.consultamedica.ObservacoesMedicasDTO;
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
    private ConsultaMedicaServicoImpl consultaServico;
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
    public ResponseEntity<Void> cadastrarAgendamento(CadastrarAgendamentoDTO dto, String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        agendamentoServico.cadastrarAgendamento(dto, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendamentos(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        return agendamentoServico.buscarAgendamentos(medico);
    }

    @Override
    public ResponseEntity<List<Agendamento>> buscarAgendamentosEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        return agendamentoServico.buscarAgendamentosEntreDatas(di, df, medico);
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsultaMedica(String crm, String dataAgendamento, ObservacoesMedicasDTO dto) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime da = Facade.conversorDataHora(dataAgendamento);
        consultaServico.adicionarObservacoesMedicasAConsultaMedica(medico, da, dto.observacoes());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(String crm) {
        Medico medico = facade.buscarMedico(crm).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = facade.buscarMedico(crm).getBody();
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                        && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody()) 
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoCliente(String crm, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicas()
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoCliente(String crm, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadas()
                .stream()
                .filter(
                        e -> e.getMedico().equals(facade.buscarMedico(crm).getBody())
                                && e.getCliente().getUsuario().getNome().equals(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoCliente(String crm, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladas()
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
    public ResponseEntity<Void> adicionarConsultaMedicaAoProntuario(String crm, String cpf, String dataAgendamento) {
        Medico medico = facade.buscarMedico(crm).getBody();
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime da = Facade.conversorDataHora(dataAgendamento);
        ConsultaMedica consulta = facade.buscarConsultaPeloMedico(da, medico).getBody();
        prontuarioServico.adicionarConsultaMedicaAoProntuario(medico, cliente, consulta);
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
