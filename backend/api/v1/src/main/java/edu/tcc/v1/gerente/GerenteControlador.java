package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GerenteControlador {

    ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto);
    ResponseEntity<Void> demitirMedico(String crm);
    ResponseEntity<List<Medico>> buscarMedicos();
    ResponseEntity<Medico> buscarMedicoPeloCRM(String crm);
    ResponseEntity<List<Cliente>> buscarClientes();
    ResponseEntity<Void> deletarMedicos();
    ResponseEntity<Void> deletarClientes();

}
