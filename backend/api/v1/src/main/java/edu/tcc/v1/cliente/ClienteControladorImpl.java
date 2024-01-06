package edu.tcc.v1.cliente;

import edu.tcc.v1.consultamedica.AgendarConsultaMedicaDTO;
import edu.tcc.v1.consultamedica.ConsultaMedica;
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
    @PostMapping("cliente")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody CadastrarClienteDTO dto) {
        return servico.cadastrarCliente(dto);
    }

    @Override
    @PostMapping("consulta/{cpf}/{crm}")
    public ResponseEntity<Void> agendarConsultaMedica(@RequestBody AgendarConsultaMedicaDTO dto, 
                                                @PathVariable(name = "crm") String crm, 
                                                @PathVariable(name = "cpf") String cpf) {
        return servico.agendarConsultaMedica(dto, crm, cpf);
    }

    @Override
    @PatchMapping("consulta/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> cancelarConsultaMedica(@PathVariable(name = "cpf") String cpf,
                                          @PathVariable(name = "dataAgendamento") String dataAgendamento) {
        return servico.cancelarConsultaMedica(cpf, dataAgendamento);
    }

    @Override
    @GetMapping("consultas/{cpf}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasMedicas(cpf);
    }

    @Override
    @GetMapping("consultas/agendadas/{cpf}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasMedicasAgendadas(cpf);
    }

    @Override
    @GetMapping("consultas/canceladas/{cpf}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasMedicasCanceladas(cpf);
    }

    @Override
    @GetMapping("consultas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/agendadas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasAgendadasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatas(@PathVariable(name = "cpf") String cpf,
                                                                              @PathVariable(name = "dataInicial") String dataInicial, 
                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasCanceladasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/entre-datas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                    @PathVariable(name = "nome") String nome,
                                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/entre-datas/agendadadas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                             @PathVariable(name = "nome") String nome,
                                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasAgendadasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                              @PathVariable(name = "nome") String nome,
                                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasMedicasCanceladasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/{cpf}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                          @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasPeloNomeDoMedico(cpf, nome);
    }

    @Override
    @GetMapping("consultas/agendadas/{cpf}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasAgendadasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                   @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasAgendadasPeloNomeDoMedico(cpf, nome);
    }

    @Override
    @GetMapping("consultas/canceladas/{cpf}/{nome}")
    public ResponseEntity<List<ConsultaMedica>> buscarConsultasMedicasCanceladasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                    @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasMedicasCanceladasPeloNomeDoMedico(cpf, nome);
    }
    
}
