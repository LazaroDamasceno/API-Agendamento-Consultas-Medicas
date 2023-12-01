package edu.tcc.v1.cliente;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaServicoImpl;
import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.telegrambot.InstanciarTelegramBot;
import edu.tcc.v1.update.CriarUpdateImpl;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServicoImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServicoImpl implements ClienteServico {

    private ClienteRepository repository;
    private UsuarioServicoImpl usuarioServico;
    private ConsultaServicoImpl consultaServico;
    private AgendaMedicaServicoImpl amServico;
    private CriarUpdateImpl criarUpdate;

    @Override
    public List<Cliente> exibirTodosOsClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente exibirClientePeloCPF(String cpf) {
        Cliente cliente = null;
        Usuario usuario = usuarioServico.exibirUsuarioPorCpf(cpf);
        Optional<Cliente> clienteOptional = repository.findByUsuario(usuario);
        if (clienteOptional.isPresent()) cliente = clienteOptional.get();
        return cliente;
    }

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = new Cliente(dto);
        repository.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cadastrarConsulta(String cpf, AgendarConsultaDTO dto) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        AgendaMedica am = amServico.exibirAgendaMedicaPelaDataDisponivel(dto.dataAgendamento());
        Medico medico = am.getMedico();
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(dto.dataAgendamento());
        consulta.setCliente(cliente);
        consulta.setMedico(medico);
        consultaServico.atualizar(consulta);
        amServico.associarConsulta(dto.dataAgendamento(), consulta);
        InstanciarTelegramBot.instanciar().onUpdateReceived(criarUpdate.criarUpdate(dto.dataAgendamento()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(String cpf, String dataAgendamento) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(LocalDateTime.parse(dataAgendamento));
        if (!consulta.getCliente().equals(cliente)) return ResponseEntity.badRequest().build();
        consultaServico.cancelarConsulta(LocalDateTime.parse(dataAgendamento));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirTodasAsConsultas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatas(LocalDateTime.parse(dataFinal), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(LocalDateTime.parse(dataFinal), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladasEntreDatas(LocalDateTime.parse(dataFinal), LocalDateTime.parse(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(String cpf, String nomeMedico) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasPeloNomeDoMedico(nomeMedico)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

}
