package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GerenteServicoImpl implements GerenteServico {

    private GerenteServicosFacade servicos;

    @Override
    public ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto) {
        servicos.getMedicoServico().cadastrarMedico(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Medico>> exibirListaDosMedicos() {
        return ResponseEntity.ok().body(servicos.getMedicoServico().exibirTodosOsMedicos());
    }

    @Override
    public ResponseEntity<Medico> exibirMedicoPeloCRM(String crm) {
        return ResponseEntity.ok().body(servicos.getMedicoServico().exibirMedicoPeloCRM(crm));
    }

    @Override
    public ResponseEntity<List<Cliente>> exibirListaDosClientes() {
        return ResponseEntity.ok().body(servicos.getClienteServico().exibirTodosOsClientes());
    }

}
