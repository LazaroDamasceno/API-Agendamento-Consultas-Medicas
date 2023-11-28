package edu.tcc.v1.medico;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.CadastrarAgendaMedicaDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.prontuario.Prontuario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
@AllArgsConstructor
public class MedicoControladorImp implements MedicoControlador {

    private MedicoServicoImp servico;

    @Override
    @GetMapping("exibir/agendasmedicas/{crm}")
    public ResponseEntity<List<AgendaMedica>> exibirTodasAsAgendasMedicas(@PathVariable String crm) {
        return servico.exibirTodasAsAgendasMedicas(crm);
    }

    @Override
    @GetMapping("exibir/agendasmedicas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<AgendaMedica>> exibirAgendasMedicasEntreDatas(@PathVariable String crm, 
                                                                             @PathVariable String dataInicial, 
                                                                             @PathVariable String dataFinal) {
        return servico.exibirAgendasMedicasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @PostMapping("cadastrar/agendamedica/{crm}")
    public ResponseEntity<Void> cadastrarAgendaMedica(@PathVariable String crm, 
                                                      @RequestBody CadastrarAgendaMedicaDTO dto) {
        return servico.cadastrarAgendaMedica(crm, dto);
    }

    @Override
    @GetMapping("exibir/consultas/{crm}")
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(@PathVariable String crm) {
        return servico.exibirTodasAsConsultas(crm);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{crm}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(@PathVariable String crm) {
        return servico.exibirConsultasAgendadas(crm);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{crm}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(@PathVariable String crm) {
        return servico.exibirConsultasCanceladas(crm);
    }

    @Override
    @GetMapping("exibir/consultas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(@PathVariable String crm,
                                                                    @PathVariable String dataInicial,
                                                                    @PathVariable String dataFinal) {
        return servico.exibirConsultasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(@PathVariable String crm,
                                                                             @PathVariable String dataInicial,
                                                                             @PathVariable String dataFinal) {
        return servico.exibirConsultasAgendadasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(@PathVariable String crm,
                                                                              @PathVariable String dataInicial,
                                                                              @PathVariable String dataFinal) {
        return servico.exibirConsultasCanceladasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/{crm}/{nomeCliente}")
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoCliente(@PathVariable String crm,
                                                                           @PathVariable String nomeCliente) {
        return servico.exibirConsultasPeloNomeDoCliente(crm, nomeCliente);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{crm}/{nomeCliente}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasPeloNomeDoCliente(@PathVariable String crm,
                                                                                    @PathVariable String nomeCliente) {
        return servico.exibirConsultasAgendadasPeloNomeDoCliente(crm, nomeCliente);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{crm}/{nomeCliente}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasPeloNomeDoCliente(@PathVariable String crm,
                                                                                     @PathVariable String nomeCliente) {
        return servico.exibirConsultasCanceladasPeloNomeDoCliente(crm, nomeCliente);
    }

    @Override
    @GetMapping("exibir/consultas/{crm}/{nomeCliente}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatasPeloNomeDoCliente(@PathVariable String crm,
                                                                                     @PathVariable String nomeCliente,
                                                                                     @PathVariable String dataInicial,
                                                                                     @PathVariable String dataFinal) {
        return servico.exibirConsultasEntreDatasPeloNomeDoCliente(crm, nomeCliente, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{crm}/{nomeCliente}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(@PathVariable String crm,
                                                                                              @PathVariable String nomeCliente,
                                                                                              @PathVariable String dataInicial,
                                                                                              @PathVariable String dataFinal) {
        return servico.exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(crm, nomeCliente, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{crm}/{nomeCliente}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(@PathVariable String crm, 
                                                                                               @PathVariable String nomeCliente,
                                                                                               @PathVariable String dataInicial,
                                                                                               @PathVariable String dataFinal) {
        return servico.exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(crm, nomeCliente, dataInicial, dataFinal);
    }

    @Override
    @PostMapping("cadastrar/prontuario/{crm}")
    public ResponseEntity<Void> cadastrarProntuario(@PathVariable String crm, 
                                                    @PathVariable String cpf) {
        return servico.cadastrarProntuario(crm, cpf);
    }

    @Override
    @GetMapping("exibir/prontuario/{crm}/{cpf}")
    public ResponseEntity<Prontuario> exibirProntuarioPeloCliente(@PathVariable String crm, 
                                                                  @PathVariable String cpf) {
        return servico.exibirProntuarioPeloCliente(crm, cpf);
    }

    @Override
    @GetMapping("exibir/prontuarios/{crm}/{cpf}")
    public ResponseEntity<List<Prontuario>> exibirTodosOsProntuarios(@PathVariable String crm) {
        return servico.exibirTodosOsProntuarios(crm);
    }

    @Override
    @GetMapping("exibir/prontuarios/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Prontuario>> exibirProntuariosEntreDatas(@PathVariable String crm, 
                                                                        @PathVariable String dataInicial, 
                                                                        @PathVariable String dataFinal) {
        return servico.exibirProntuariosEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @PatchMapping("adicionar/consulta/prontuario/{crm}/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> adicionarConsultaAoProntuario(String crm, String cpf, String dataAgendamento) {
        return null;
    }

}
