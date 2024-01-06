package edu.tcc.v1.facade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tcc.v1.agendamento.Agendamento;
import edu.tcc.v1.agendamento.AgendamentoRepositorio;
import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.cliente.ClienteRepositorio;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaRepositorio;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.medico.MedicoRepositorio;
import edu.tcc.v1.prontuario.Prontuario;
import edu.tcc.v1.prontuario.ProntuarioRepositorio;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioRepositorio;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Facade {

    private final AgendamentoRepositorio agendamentoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ConsultaRepositorio consultaRepositorio;
    private final MedicoRepositorio medicoRepositorio;
    private final ProntuarioRepositorio prontuarioRepositorio;

    public ResponseEntity<Agendamento> buscarAgendamento(LocalDateTime dataDisponivel, Medico medico) {
        Optional<Agendamento> agendamento = agendamentoRepositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataDisponivel().equals(dataDisponivel)
                                && e.getMedico().equals(medico)
                ).findFirst();
        return agendamento
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Cliente> buscarCliente(String cpf) {
        Usuario usuario = buscarUsuarioPeloCPF(cpf);
        return clienteRepositorio
                .findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Consulta> buscarConsultaPeloMedico(LocalDateTime dataHora, Medico medico) {
        Optional<Consulta> consulta = consultaRepositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getMedico().equals(medico)
                ).findFirst();
        return consulta
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Consulta> buscarConsultaPeloCliente(LocalDateTime dataHora, Cliente cliente) {
        Optional<Consulta> consulta = consultaRepositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getCliente().equals(cliente)
                ).findFirst();
        return consulta
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Medico> buscarMedico(String crm) {
        return medicoRepositorio
                .findByCrm(crm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Prontuario> buscarProntuarioPorCliente(Medico medico, Cliente cliente) {
        Optional<Prontuario> prontuario = prontuarioRepositorio.findByCliente(cliente);
        if (prontuario.isEmpty() || !prontuario.get().getMedico().equals(medico)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prontuario.get());
    }

    public Usuario buscarUsuarioPeloCPF(String cpf) {
        return usuarioRepositorio.buscarUsuario(cpf);
    }

    public static LocalDateTime conversorDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    
}
