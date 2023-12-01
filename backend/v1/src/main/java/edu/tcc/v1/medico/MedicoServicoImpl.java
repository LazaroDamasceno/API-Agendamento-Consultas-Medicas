package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaServicoImpl;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
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
    private AgendaMedicaServicoImpl amServico;
    private ConsultaServicoImpl consultaServico;
    private ProntuarioServicoImpl prontuarioServico;

    @Override
    public Medico exibirMedicoPeloCRM(String crm) {
        return repositorio.findByCrm(crm).orElse(null);
    }

    @Override
    public List<Medico> exibirTodosOsMedicos() {
        return repositorio.findAll();
    }

    @Override
    public List<Medico> exibirTodosMedicosAtivos() {
        return repositorio
                .findAll()
                .stream()
                .filter(e -> e.getDataDemissao() == null)
                .toList();
    }

    @Override
    public List<Medico> exibirTodosMedicosDemitidos() {
        return repositorio
                .findAll()
                .stream()
                .filter(e -> e.getDataDemissao() != null)
                .toList();
    }

    @Override
    public void cadastrarMedico(CadastrarMedicoDTO dto) {
        Medico medico = new Medico(dto);
        repositorio.save(medico);
    }

    @Override
    public void demitirMedico(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        medico.setDataDemissao(LocalDateTime.now());
        repositorio.saveAndFlush(medico);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> exibirTodasAsAgendasMedicas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<AgendaMedica> am = amServico
                .exibirTodasAsAgendasMedicas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(am);
    }

    @Override
    public ResponseEntity<List<AgendaMedica>> exibirAgendasMedicasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<AgendaMedica> am = amServico
                .exibirAgendasMedicasEntreDatas(LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(am);
    }

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(String crm, CadastrarAgendaMedicaDTO dto) {
        Medico medico = exibirMedicoPeloCRM(crm);
        amServico.cadastrarAgendaMedica(dto);
        amServico.associarMedico(dto.dataDisponivel(), medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirTodasAsConsultas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatas(LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasPeloNomeDoCliente(String crm, String nomeCliente) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladasPeloNomeDoCliente(nomeCliente)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatasPeloNomeDoCliente(nomeCliente, LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(nomeCliente, LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(nomeCliente, LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<Void> cadastrarProntuario(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        prontuarioServico.cadastrarProntuario(cpf);
        prontuarioServico.associarMedico(cpf, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Prontuario> exibirProntuarioPeloCliente(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Prontuario prontuario = prontuarioServico.exibirProntuarioPeloCliente(cpf);
        if (!prontuario.getMedico().equals(medico)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(prontuario);
    }

    @Override
    public ResponseEntity<List<Prontuario>> exibirTodosOsProntuarios(String crm) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Prontuario> prontuarios = prontuarioServico
                .exibirTodosOsProntuarios()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(prontuarios);
    }

    @Override
    public ResponseEntity<List<Prontuario>> exibirProntuariosEntreDatas(String crm, String dataInicial, String dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Prontuario> prontuarios = prontuarioServico
                .exibirProntuariosEntreDatas(LocalDateTime.parse(dataInicial), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(prontuarios);
    }

    @Override
    public ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, String dataAgendamento) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(LocalDateTime.parse(dataAgendamento));
        if (!consulta.getMedico().equals(medico)) return ResponseEntity.badRequest().build();
        prontuarioServico.adicionarConsulta(cpf, consulta);
        return ResponseEntity.noContent().build();
    }

}
