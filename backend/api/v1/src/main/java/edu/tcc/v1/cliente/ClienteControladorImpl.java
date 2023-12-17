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
    
    private ClienteServicoImp servico;
    
    @Override
    @PostMapping("cadastrar/cliente")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody CadastrarClienteDTO dto) {
        return servico.cadastrarCliente(dto);
    }

    @Override
    public ResponseEntity<Void> agendarConsulta(@RequestBody AgendarConsultaDTO dto, 
                                                @PathVariable(name = "crm") String crm, 
                                                @PathVariable(name = "cpf") String cpf) {
        return servico.agendarConsulta(dto, crm, cpf);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultas(cpf);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasAgendadas(cpf);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasCanceladas(cpf);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(@PathVariable(name = "cpf") String cpf,
                                                                              @PathVariable(name = "dataInicial") String dataInicial, 
                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                    @PathVariable(name = "crm") String crm, 
                                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatasPeloNomeDoMedico(cpf, crm, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                             @PathVariable(name = "crm") String crm, 
                                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(cpf, crm, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                              @PathVariable(name = "crm") String crm, 
                                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(cpf, crm, dataInicial, dataFinal);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                          @PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasPeloNomeDoMedico(cpf, crm);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                   @PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasAgendadasPeloNomeDoMedico(cpf, crm);
    }

    @Override
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                    @PathVariable(name = "crm") String crm) {
        return servico.buscarConsultasCanceladasPeloNomeDoMedico(cpf, crm);
    }
    
}
