package edu.tcc.v1.medico;

import edu.tcc.v1.agendamento.Agendamento;
import edu.tcc.v1.agendamento.CadastrarAgendamentoDTO;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.consultamedica.ObservacoesMedicasDTO;
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
    public ResponseEntity<Void> cadastrarAgendamento(@RequestBody CadastrarAgendamentoDTO dto,
                                                      @PathVariable(name = "crm") String crm) {
        return servico.cadastrarAgendamento(dto, crm);
    }

    @Override
    @GetMapping("agendamentos/{crm}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentos(@PathVariable(name = "crm") String crm) {
        return servico.buscarAgendamentos(crm);
    }

    @Override
    @GetMapping("agendamentos/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentosEntreDatas(@PathVariable(name = "crm") String crm,
                                                                             @PathVariable(name = "dataInicial") String dataInicial,
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarAgendamentosEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @PatchMapping("observacoes-medicas/{crm}/{dataAgendamento}")
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(@PathVariable(name = "crm") String crm,
                                                                     @PathVariable(name = "dataAgendamento") String dataAgendamento,
                                                                     @RequestBody ObservacoesMedicasDTO dto) {
        return servico.adicionarObservacoesMedicasAConsultaMedica(crm, dataAgendamento, dto);
    }

    @Override
    @GetMapping("consultas-medicas/{crm}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasMedicas(crm);
    }

    @Override
    @GetMapping("consultas-medicas/agendadas/{crm}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasMedicasAgendadas(crm);
    }

    @Override
    @GetMapping("consultas-medicas/canceladas/{crm}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(@PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasMedicasCanceladas(crm);
    }

    @Override
    @GetMapping("consultas-medicas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                    @PathVariable(name = "dataInicial") String dataInicial,
                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/agendadas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                             @PathVariable(name = "dataInicial") String dataInicial,
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasAgendadasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/canceladas/entre-datas/{crm}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(@PathVariable(name = "crm") String crm,
                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasCanceladasEntreDatas(crm, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                     @PathVariable(name = "nome") String nome,
                                                                                     @PathVariable(name = "dataInicial") String dataInicial,
                                                                                     @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/agendadas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                              @PathVariable(name = "nome") String nome,
                                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/canceladas/entre-datas/nome-cliente/{crm}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                               @PathVariable(name = "nome") String nome,
                                                                                               @PathVariable(name = "dataInicial") String dataInicial,
                                                                                               @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoCliente(crm, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas-medicas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                           @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @GetMapping("consultas-medicas/agendadas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                    @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasAgendadasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @GetMapping("consultas-medicas/canceladas/nome-cliente/{crm}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoCliente(@PathVariable(name = "crm") String crm,
                                                                                     @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasCanceladasPeloNomeDoCliente(crm, nome);
    }

    @Override
    @PostMapping("prontuario/{crm}/{cpf}")
    public ResponseEntity<Void> criarProntuario(@PathVariable(name = "crm") String crm,
                                                @PathVariable(name = "cpf") String cpf) {
        return servico.criarProntuario(crm, cpf);
    }

    @Override
    @PatchMapping("consulta-medica/prontuario/{crm}/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> adicionarConsultaMedicaAoProntuario(@PathVariable(name = "crm") String crm,
                                                              @PathVariable(name = "cpf") String cpf,
                                                              @PathVariable(name = "dataAgendamento") String dataAgendamento) {
        return servico.adicionarConsultaMedicaAoProntuario(crm, cpf, dataAgendamento);
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
