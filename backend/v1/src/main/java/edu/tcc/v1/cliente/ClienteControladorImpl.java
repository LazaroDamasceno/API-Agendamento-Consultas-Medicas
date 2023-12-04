package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor
public class ClienteControladorImpl implements ClienteControlador {

    private ClienteServicoImpl servico;

    @Override
    @PostMapping("cadastrar/cliente")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody CadastrarClienteDTO dto) {
        return servico.cadastrarCliente(dto);
    }

    @Override
    @PostMapping("cadastrar/consulta/{cpf}")
    public ResponseEntity<Void> cadastrarConsulta(@PathVariable String cpf,
                                                  @RequestBody AgendarConsultaDTO dto) {
        return servico.cadastrarConsulta(cpf, dto);
    }

    @Override
    @PatchMapping("cancelar/consulta/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable String cpf,
                                                 @PathVariable String dataAgendamento) {
        return servico.cancelarConsulta(cpf, dataAgendamento);
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(@PathVariable String cpf) {
        return servico.exibirTodasAsConsultas(cpf);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(@PathVariable String cpf) {
        return servico.exibirConsultasAgendadas(cpf);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(@PathVariable String cpf) {
        return servico.exibirConsultasCanceladas(cpf);
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(@PathVariable String cpf,
                                                                    @PathVariable String dataInicial,
                                                                    @PathVariable String dataFinal) {
        return servico.exibirConsultasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(@PathVariable String cpf,
                                                                             @PathVariable String dataInicial,
                                                                             @PathVariable String dataFinal) {
        return servico.exibirConsultasAgendadasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(@PathVariable String cpf,
                                                                              @PathVariable String dataInicial,
                                                                              @PathVariable String dataFinal) {
        return servico.exibirConsultasCanceladasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}/{nomeMedico}")
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(@PathVariable String cpf,
                                                                          @PathVariable String nomeMedico) {
        return servico.exibirConsultasPeloNomeDoMedico(cpf, nomeMedico);
    }

}
