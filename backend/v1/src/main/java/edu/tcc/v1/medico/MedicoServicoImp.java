package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaServicoImp;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImp;
import edu.tcc.v1.prontuario.Prontuario;
import edu.tcc.v1.prontuario.ProntuarioServicoImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicoServicoImp implements MedicoServico {

    private MedicoRepositorio repositorio;
    private AgendaMedicaServicoImp amServico;
    private ConsultaServicoImp consultaServico;
    private ProntuarioServicoImp prontuarioServico;

    @Override
    public Medico exibirMedicoPeloCRM(String crm) {
        Medico medico = null;
        Optional<Medico> medicoOptional = repositorio.findByCrm(crm);
        if (medicoOptional.isPresent()) medico = medicoOptional.get();
        return medico;
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
    public ResponseEntity<List<AgendaMedica>> exibirAgendasMedicasEntreDatas(String crm, LocalDateTime dataIncial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<AgendaMedica> am = amServico
                .exibirAgendasMedicasEntreDatas(dataIncial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(am);
    }

    @Override
    public ResponseEntity<Void> cadastrarAgendaMedica(String crm, CadastrarAgendaMedicaDTO dto) {
        Medico medico = exibirMedicoPeloCRM(crm);
        amServico.cadastrarAgendaMedica(dto);
        AgendaMedica am = amServico.exibirAgendaMedicaPelaDataDisponivel(dto.dataDisponivel());
        amServico.associarMedico(dto.dataDisponivel(), medico);
        amServico.atualizar(am);
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
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatas(dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(dataInicial, dataFinal)
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
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String crm, String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<Void> cadastrarProntuario(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        prontuarioServico.cadastrarProntuario(cpf);
        Prontuario prontuario = prontuarioServico.exibirProntuarioPeloCliente(cpf);
        prontuario.setMedico(medico);
        prontuarioServico.atualizar(prontuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Prontuario> exibirProntuarioPeloCliente(String crm, String cpf) {
        Medico medico = exibirMedicoPeloCRM(crm);
        Prontuario prontuario = prontuarioServico.exibirProntuarioPeloCliente(cpf);
        if (!prontuario.getMedico().equals(medico)) ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(prontuario);
    }

    @Override
    public ResponseEntity<List<Prontuario>> exibirProntuariosEntreDatas(String crm, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Medico medico = exibirMedicoPeloCRM(crm);
        List<Prontuario> prontuarios = prontuarioServico
                .exibirProntuariosEntreDatas(dataInicial, dataFinal)
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok().body(prontuarios);
    }

}
