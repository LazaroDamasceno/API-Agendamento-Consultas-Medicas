package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ObservacoesMedicasDTO;
import edu.tcc.v1.conversor.dataHora.ConversorDataHora;
import edu.tcc.v1.prontuario.Prontuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicoServicoImpl implements MedicoServico {

    private MedicoServicosFacade servicos;

    @Override
    public Medico exibirMedicoPeloCRM(String crm) {
        return servicos.getRepositorio().findByCrm(crm).orElse(null);
    }

    @Override
    public List<Medico> exibirTodosOsMedicos() {
        return servicos.getRepositorio().findAll();
    }

    @Override
    public List<Medico> exibirTodosMedicosAtivos() {
        return servicos.getRepositorio()
                .findAll()
                .stream()
                .filter(e -> e.getDataDemissao() == null)
                .toList();
    }

    @Override
    public List<Medico> exibirTodosMedicosDemitidos() {
        return servicos.getRepositorio()
                .findAll()
                .stream()
                .filter(e -> e.getDataDemissao() != null)
                .toList();
    }

    @Override
    public void cadastrarMedico(CadastrarMedicoDTO dto) {
        Medico medico = new Medico(dto);
        servicos.getRepositorio().save(medico);
    }

    @Override
    public void demitirMedico(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        medico.setDataDemissao(LocalDateTime.now());
        servicos.getRepositorio().saveAndFlush(medico);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> exibirTodasAsAgendasMedicas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<AgendaMedica> am = servicos.getAmServico()
                .exibirTodasAsAgendasMedicas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(am);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> exibirAgendasMedicasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<AgendaMedica> am = servicos.getAmServico()
                .exibirAgendasMedicasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(am);
    }

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(String crm, CadastrarAgendaMedicaDTO dto) {
        Medico medico = exibirMedicoPeloCRM(crm);
        servicos.getAmServico().cadastrarAgendaMedica(dto);
        servicos.getAmServico().associarMedico(ConversorDataHora.conversor(dto.dataDisponivel()), medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirTodasAsConsultas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasEntreDatasPeloNomeDoCliente(nomeCliente, ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(nomeCliente, ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(nomeCliente, ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<Void> cadastrarProntuario(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        servicos.getProntuarioServico().cadastrarProntuario(cpf);
        servicos.getProntuarioServico().associarMedico(cpf, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Prontuario> exibirProntuarioPeloCliente(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Prontuario prontuario = servicos.getProntuarioServico().exibirProntuarioPeloCliente(cpf);
        if (!prontuario.getMedico().equals(medico)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(prontuario);
    }

    @Override
    public ResponseEntity<List<Prontuario>> exibirTodosOsProntuarios(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Prontuario> prontuarios = servicos.getProntuarioServico()
                .exibirTodosOsProntuarios()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(prontuarios);
    }

    @Override
    public ResponseEntity<List<Prontuario>> exibirProntuariosEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Prontuario> prontuarios = servicos.getProntuarioServico()
                .exibirProntuariosEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(prontuarios);
    }

    @Override
    public ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, String dataAgendamento) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Consulta consulta = servicos.getConsultaServico().exibirConsultaPelaDataDeAgendamento(ConversorDataHora.conversor(dataAgendamento));
        if (!consulta.getMedico().equals(medico)) return ResponseEntity.badRequest().build();
        servicos.getProntuarioServico().adicionarConsulta(cpf, consulta);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicas(String crm, String dataAgendamento, ObservacoesMedicasDTO dto) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Consulta consulta = servicos.getConsultaServico().exibirConsultaPelaDataDeAgendamento(ConversorDataHora.conversor(dataAgendamento));
        if (!consulta.getMedico().equals(medico)) return ResponseEntity.badRequest().build();
        servicos.getConsultaServico().adicionarObservacoesMedicas(ConversorDataHora.conversor(dataAgendamento), dto.observacoes());
        return ResponseEntity.noContent().build(); 
    }

}
