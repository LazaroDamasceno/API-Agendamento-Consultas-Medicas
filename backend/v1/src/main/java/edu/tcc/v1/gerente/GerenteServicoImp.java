package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.cliente.ClienteServicoImp;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.medico.MedicoServicoImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GerenteServicoImp implements GerenteServico {

    private MedicoServicoImp medicoServico;
    private ClienteServicoImp clienteServico;

    @Override
    public ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Medico>> exibirListaDosMedicos() {
        return ResponseEntity.ok().body(medicoServico.exibirTodosOsMedicos());
    }

    @Override
    public ResponseEntity<Medico> exibirMedicoPeloCRM(String crm) {
        return ResponseEntity.ok().body(medicoServico.exibirMedicoPeloCRM(crm));
    }

    @Override
    public ResponseEntity<List<Cliente>> exibirListaDosClientes() {
        return ResponseEntity.ok().body(clienteServico.exibirTodosOsClientes());
    }

}
