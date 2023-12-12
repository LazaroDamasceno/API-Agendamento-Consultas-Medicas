package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GerenteServico {

    ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto);
    ResponseEntity<List<Medico>> exibirListaDosMedicos();
    ResponseEntity<Medico> exibirMedicoPeloCRM(String crm);
    ResponseEntity<List<Cliente>> exibirListaDosClientes();

}
