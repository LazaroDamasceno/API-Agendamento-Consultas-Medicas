package edu.tcc.v1.cliente;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.conversor.dataHora.ConversorDataHora;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.usuario.Usuario;
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

    private ClienteServicosFacade servicos;

    @Override
    public List<Cliente> exibirTodosOsClientes() {
        return servicos.getRepository().findAll();
    }

    @Override
    public Cliente exibirClientePeloCPF(String cpf) {
        Cliente cliente = null;
        Usuario usuario = servicos.getUsuarioServico().exibirUsuarioPorCpf(cpf);
        Optional<Cliente> clienteOptional = servicos.getRepository().findByUsuario(usuario);
        if (clienteOptional.isPresent()) cliente = clienteOptional.get();
        return cliente;
    }

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = new Cliente(dto);
        servicos.getRepository().save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cadastrarConsulta(String cpf, AgendarConsultaDTO dto) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        AgendaMedica am = servicos.getAmServico().exibirAgendaMedicaPelaDataDisponivel(ConversorDataHora.conversor(dto.dataAgendamento()));
        Medico medico = am.getMedico();
        servicos.getConsultaServico().cadastrarConsulta(dto);
        Consulta consulta = servicos.getConsultaServico().exibirConsultaPelaDataDeAgendamento(ConversorDataHora.conversor(dto.dataAgendamento()));
        servicos.getConsultaServico().associarCliente(ConversorDataHora.conversor(dto.dataAgendamento()), cliente);
        servicos.getConsultaServico().associarMedico(ConversorDataHora.conversor(dto.dataAgendamento()), medico);
        servicos.getAmServico().associarConsulta(ConversorDataHora.conversor(dto.dataAgendamento()), consulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(String cpf, String dataAgendamento) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        Consulta consulta = servicos.getConsultaServico().exibirConsultaPelaDataDeAgendamento(LocalDateTime.parse(dataAgendamento));
        if (!consulta.getCliente().equals(cliente)) return ResponseEntity.badRequest().build();
        servicos.getConsultaServico().cancelarConsulta(LocalDateTime.parse(dataAgendamento));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirTodasAsConsultas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String cpf) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasAgendadasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasCanceladasEntreDatas(ConversorDataHora.conversor(dataInicial), ConversorDataHora.conversor(dataFinal))
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(String cpf, String nomeMedico) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = servicos.getConsultaServico()
                .exibirConsultasPeloNomeDoMedico(nomeMedico)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

}
