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
public class GerenteControladorImpl implements GerenteServico {

    private GerenteServicoImpl servico;

    @Override
    @PostMapping("cadastrar/medico")
    public ResponseEntity<Void> cadastrarMedico(@RequestBody CadastrarMedicoDTO dto) {
        return servico.cadastrarMedico(dto);
    }

    @Override
    @PatchMapping("demitir/medico/{crm}")
    public ResponseEntity<Void> demitirMedico(@PathVariable(name = "crm") String crm) {
        return servico.demitirMedico(crm);
    }

    @Override
    @GetMapping("buscar/medicos")
    public ResponseEntity<List<Medico>> buscarMedicos() {
        return servico.buscarMedicos();
    }

    @Override
    @GetMapping("buscar/medico/crm/{crm}")
    public ResponseEntity<Medico> buscarMedicoPeloCRM(@PathVariable(name = "crm") String crm) {
        return servico.buscarMedicoPeloCRM(crm);
    }

    @Override
    @GetMapping("buscar/clientes")
    public ResponseEntity<List<Cliente>> buscarClientes() {
        return servico.buscarClientes();
    }

}
