package edu.tcc.v1.medico;

import edu.tcc.v1.agendamento.Agendamento;
import edu.tcc.v1.agendamento.CadastrarAgendamentoDTO;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ObservacoesMedicasDTO;
import edu.tcc.v1.prontuario.Prontuario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicos")
@AllArgsConstructor
public class MedicoControladorImpl implements MedicoControlador {

    private MedicoServicoImpl servico;

    @Override
    @PostMapping("agendamento/{crm}")
    public ResponseEntity<Void> cadastrarAgendaMedica(@RequestBody CadastrarAgendamentoDTO dto,
                                                      @PathVariable(name = "crm") String crm) {
        return servico.cadastrarAgendaMedica(dto, crm);
    }

    @Override
    @GetMapping("agendamentos/{crm}")
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicas(@PathVariable(name = "crm") String crm) {
        return servico.buscarAgendasMedicas(crm);
    }

    @Override
    @GetMapping("agendamentos/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Agendamento>> buscarAgendasMedicasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                             @PathVariable(name = "dataInicial") String dataInicial,
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarAgendasMedicasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @PatchMapping("observacoes-medicas/{crm}/{dataAgendamento}")
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(@PathVariable(name = "crm") String crm,
                                                                     @PathVariable(name = "dataAgendamento") String dataAgendamento,
                                                                     @RequestBody ObservacoesMedicasDTO dto) {
        return servico.adicionarObservacoesMedicasAConsulta(crm, dataAgendamento, dto);
    }

    @Override
    @GetMapping("consultas/{crm}")
    public ResponseEntity<List<Consulta>> buscarConsultas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultas(crm);
    }

    @Override
    @GetMapping("consultas/agendadas/{crm}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasAgendadas(crm);
    }

    @Override
    @GetMapping("consultas/canceladas/{crm}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasCanceladas(crm);
    }

    @Override
    @GetMapping("consultas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                    @PathVariable(name = "dataInicial") String dataInicial,
                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/agendadas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                             @PathVariable(name = "dataInicial") String dataInicial,
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                     @PathVariable(name = "nome") String nome,
                                                                                     @PathVariable(name = "dataInicial") String dataInicial,
                                                                                     @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/agendadas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                              @PathVariable(name = "nome") String nome,
                                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                               @PathVariable(name = "nome") String nome,
                                                                                               @PathVariable(name = "dataInicial") String dataInicial,
                                                                                               @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                           @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @GetMapping("consultas/agendadas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                    @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasAgendadasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @GetMapping("consultas/canceladas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                     @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasCanceladasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @PostMapping("prontuario/{crm}/{cpf}")
    public ResponseEntity<Void> criarProntuario(@PathVariable(name = "crm") String crm,
                                                @PathVariable(name = "cpf") String cpf) {
        return servico.criarProntuario(crm, cpf);
    }

    @Override
    @PatchMapping("consulta/prontuario/{crm}/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> adicionarConsultaAoProntuario(@PathVariable(name = "crm") String crm,
                                                              @PathVariable(name = "cpf") String cpf,
                                                              @PathVariable(name = "dataAgendamento") String dataAgendamento) {
        return servico.adicionarConsultaAoProntuario(crm, cpf, dataAgendamento);
    }

    @Override
    @GetMapping("prontuario/cliente/{crm}/{cpf}")
    public ResponseEntity<Prontuario> buscarProntuarioDeCliente(@PathVariable(name = "crm") String crm,
                                                                @PathVariable(name = "cpf") String cpf) {
        return servico.buscarProntuarioDeCliente(crm, cpf);
    }

    @Override
    @GetMapping("prontuarios/{crm}")
    public ResponseEntity<List<Prontuario>> buscarProntuarios(@PathVariable(name = "crm") String crm) {
        return servico.buscarProntuarios(crm);
    }

}
