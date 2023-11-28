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
public class ClienteControladorImp implements ClienteControlador {

    private ClienteServicoImp servico;

    @Override
    public ResponseEntity<Void> cadastrarCliente(CadastrarClienteDTO dto) {
        return null;
    }

    @Override
    @PostMapping("cadastrar/consulta/{cpf}")
    public ResponseEntity<Void> cadastrarConsulta(String cpf,
                                                  AgendarConsultaDTO dto) {
        return null;
    }

    @Override
    @PatchMapping("cancelar/consulta/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> cancelarConsulta(String cpf,
                                                 String dataAgendamento) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirTodasAsConsultas(String cpf) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadas(String cpf) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{cpf}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladas(String cpf) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasEntreDatas(String cpf,
                                                                    String dataInicial,
                                                                    String dataFinal) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/agendadas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasAgendadasEntreDatas(String cpf,
                                                                             String dataInicial,
                                                                             String dataFinal) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/canceladas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> exibirConsultasCanceladasEntreDatas(String cpf,
                                                                              String dataInicial,
                                                                              String dataFinal) {
        return null;
    }

    @Override
    @GetMapping("exibir/consultas/{cpf}/{nomeMedico}")
    public ResponseEntity<List<Consulta>> exibirConsultasPeloNomeDoMedico(String cpf,
                                                                          String nomeMedico) {
        return null;
    }

}
