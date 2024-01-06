package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.facade.Facade;
import edu.tcc.v1.facade.ConversorDataHora;
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
    private ConsultaServicoImpl consultaServico;
    private Facade auxiliaresFacade;

    @Override
    public ResponseEntity<List<Cliente>> exibirClientes() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = ClienteRepositorio.instaciar(dto);
        repositorio.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, String crm, String cpf) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        Medico medico = auxiliaresFacade.getMedico().buscar(crm).getBody();
        consultaServico.agendarConsulta(dto, cliente, medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(String cpf, String dataAgendamento) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        LocalDateTime da = ConversorDataHora.conversorDataHora(dataAgendamento);    
        consultaServico.cancelarConsulta(da, cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultas(String cpf) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(String cpf) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(String cpf) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        LocalDateTime dataInicial2 = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime dataFinal2 = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasEntreDatas(dataInicial2, dataFinal2)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadasEntreDatas(di, df)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = auxiliaresFacade.getCliente().buscar(cpf).getBody();
        LocalDateTime dataInicial2 = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime dataFinal2 = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladasEntreDatas(dataInicial2, dataFinal2)
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(auxiliaresFacade.getCliente().buscar(cpf).getBody())
                       && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(auxiliaresFacade.getCliente().buscar(cpf).getBody())
                        && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(String cpf, String nome, String dataInicial, String dataFinal) {
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(auxiliaresFacade.getCliente().buscar(cpf).getBody())
                        && e.getMedico().getUsuario().getNome().contains(nome)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(String cpf, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(String cpf, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(String cpf, String nome) {
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(e -> e.getMedico().getUsuario().getNome().contains(nome))
                .toList();
        return ResponseEntity.ok(consultas);
    }

}
