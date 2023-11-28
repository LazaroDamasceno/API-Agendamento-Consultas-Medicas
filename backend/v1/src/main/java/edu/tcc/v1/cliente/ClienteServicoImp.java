package edu.tcc.v1.cliente;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaServicoImp;
import edu.tcc.v1.consulta.AgendarConsulta;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImp;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServicoImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServicoImp implements ClienteServico {

    private ClienteRepository repository;
    private UsuarioServicoImp usuarioServico;
    private ConsultaServicoImp consultaServico;
    private AgendaMedicaServicoImp amServico;

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
    public ResponseEntity<Void> cadastrarConsulta(String cpf, AgendarConsulta dto) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        AgendaMedica am = amServico.exibirAgendaMedicaPelaDataDisponivel(dto.dataAgendamento());
        Medico medico = am.getMedico();
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(dto.dataAgendamento());
        consulta.setCliente(cliente);
        consulta.setMedico(medico);
        consultaServico.atualizar(consulta);
        amServico.associarConsulta(dto.dataAgendamento(), consulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(String cpf, LocalDateTime dataAgendamento) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        if (!consulta.getCliente().equals(cliente)) return ResponseEntity.badRequest().build();
        consultaServico.cancelarConsulta(dataAgendamento);
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
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasEntreDatas(dataFinal, dataFinal)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasAgendadasEntreDatas(dataFinal, dataFinal)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok().body(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Cliente cliente = exibirClientePeloCPF(cpf);
        List<Consulta> consultas = consultaServico
                .exibirConsultasCanceladasEntreDatas(dataFinal, dataFinal)
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
