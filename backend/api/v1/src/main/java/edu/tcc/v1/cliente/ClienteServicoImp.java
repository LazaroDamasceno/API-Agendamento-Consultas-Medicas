package edu.tcc.v1.cliente;

import edu.tcc.v1.auxiliares.ConversorDataHora;
import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.medico.MedicoServicoImpl;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServicoImp implements ClienteServico {

    static ClienteRepositorio repositorio;
    private ConsultaServicoImpl consultaServico;

    public static ResponseEntity<Cliente> buscarClientePeloCPF(String cpf) {
        Usuario usuario = UsuarioServico.buscarUsuarioPeloCPF(cpf).getBody();
        return repositorio
                .findByUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        Cliente cliente = ClienteRepositorio.instaciar(dto);
        repositorio.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, String crm, String cpf) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        consultaServico.agendarConsulta(dto, buscarClientePeloCPF(crm).getBody(), medico);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultas(String cpf) {
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(String cpf) {
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(String cpf) {
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(e -> e.getCliente().equals(cliente))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(String cpf, String dataInicial, String dataFinal) {
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
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
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
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
        Cliente cliente = buscarClientePeloCPF(cpf).getBody();
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
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(buscarClientePeloCPF(cpf).getBody())
                       && e.getMedico().equals(medico)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(buscarClientePeloCPF(cpf).getBody())
                                && e.getMedico().equals(medico)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(String cpf, String crm, String dataInicial, String dataFinal) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        LocalDateTime di = ConversorDataHora.conversorDataHora(dataInicial);
        LocalDateTime df = ConversorDataHora.conversorDataHora(dataFinal);
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladasEntreDatas(di, df)
                .stream()
                .filter(
                        e -> e.getCliente().equals(buscarClientePeloCPF(cpf).getBody())
                                && e.getMedico().equals(medico)
                ).toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(String cpf, String crm) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(String cpf, String crm) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasAgendadas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(String cpf, String crm) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        List<Consulta> consultas = consultaServico
                .buscarConsultasCanceladas()
                .stream()
                .filter(e -> e.getMedico().equals(medico))
                .toList();
        return ResponseEntity.ok(consultas);
    }

}
