package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProntuarioServico {

    ResponseEntity<Void> criarProntuario(Medico medico, Cliente cliente);
    ResponseEntity<Void> adicionarConsultaAoProntuario(Medico medico, Cliente cliente, Consulta consulta);
    ResponseEntity<List<Prontuario>> buscarProntuariosPorMedico(Medico medico);

}
