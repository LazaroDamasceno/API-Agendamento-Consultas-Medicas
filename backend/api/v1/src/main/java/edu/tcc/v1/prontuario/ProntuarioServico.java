package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProntuarioServico {

    void criarProntuario(Medico medico, Cliente cliente);
    void adicionarConsultaAoProntuario(Cliente cliente, Consulta consulta);
    List<Prontuario> buscarProntuariosPorMedico(Medico medico);

}
