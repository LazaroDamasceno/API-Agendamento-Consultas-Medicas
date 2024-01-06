package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProntuarioServico {

    ResponseEntity<Void> criarProntuario(Medico medico, Cliente cliente);
    ResponseEntity<Void> adicionarConsultaMedicaAoProntuario(Medico medico, Cliente cliente, ConsultaMedica consultaMedica);
    ResponseEntity<List<Prontuario>> buscarProntuariosPorMedico(Medico medico);

}
