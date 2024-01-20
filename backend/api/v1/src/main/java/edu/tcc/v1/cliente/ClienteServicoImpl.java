package edu.tcc.v1.cliente;

import edu.tcc.v1.consultamedica.AgendarConsultaMedicaDTO;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.consultamedica.ConsultaMedicaServicoImpl;
import edu.tcc.v1.facade.Facade;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServicoImpl implements ClienteServico {

    private ClienteRepositorio repositorio;
    private ConsultaMedicaServicoImpl consultaServico;
    private Facade facade;

    @Override
    public ResponseEntity<List<Cliente>> exibirClientes() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = ClienteRepositorio.instaciar(dto);
        if (cliente == null) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> agendarConsultaMedica(AgendarConsultaMedicaDTO dto, String crm, String cpf) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        Medico medico = facade.buscarMedico(crm).getBody();
        consultaServico.agendarConsultaMedica(dto, cliente, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsultaMedica(String cpf, String dataAgendamento) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime da = Facade.conversorDataHora(dataAgendamento);    
        consultaServico.cancelarConsultaMedica(da, cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(String cpf) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(String cpf) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(String cpf) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime dataInicial2 = Facade.conversorDataHora(dataInicial);
        LocalDateTime dataFinal2 = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasEntreDatas(dataInicial2, dataFinal2)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = facade.buscarCliente(cpf).getBody();
        LocalDateTime dataInicial2 = Facade.conversorDataHora(dataInicial);
        LocalDateTime dataFinal2 = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladasEntreDatas(dataInicial2, dataFinal2)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(facade.buscarCliente(cpf).getBody())
                       && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(facade.buscarCliente(cpf).getBody())
                        && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = Facade.conversorDataHora(dataInicial);
        LocalDateTime df = Facade.conversorDataHora(dataFinal);
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(facade.buscarCliente(cpf).getBody())
                        && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoMedico(String cpf, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoMedico(String cpf, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasAgendadas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoMedico(String cpf, String nome) {
        List<ConsultaMedica> consultas = consultaServico
                .buscarConsultasMedicasCanceladas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

}
