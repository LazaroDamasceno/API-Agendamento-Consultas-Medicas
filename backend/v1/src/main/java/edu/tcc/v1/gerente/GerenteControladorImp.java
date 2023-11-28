package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gerente")
@AllArgsConstructor
public class GerenteControladorImp implements GerenteControlador {

    private GerenteServicoImp servico;

    @Override
    @PostMapping("cadastrar/medico")
    public ResponseEntity<Void> cadastrarMedico(@RequestBody CadastrarMedicoDTO dto) {
        return servico.cadastrarMedico(dto);
    }

    @Override
    @GetMapping("exibir/lista/medicos")
    public ResponseEntity<List<Medico>> exibirListaDosMedicos() {
        return servico.exibirListaDosMedicos();
    }

    @Override
    @GetMapping("exibir/dados/medico/{crm}")
    public ResponseEntity<Medico> exibirMedicoPeloCRM(@PathVariable String crm) {
        return servico.exibirMedicoPeloCRM(crm);
    }

    @Override
    @GetMapping("exibir/lista/clientes")
    public ResponseEntity<List<Cliente>> exibirListaDosClientes() {
        return servico.exibirListaDosClientes();
    }

}
