package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProntuarioServicoImpl implements ProntuarioServico {

    private static ProntuarioRepositorio repositorio;

    public static ResponseEntity<Prontuario> buscarProntuarioPorCliente(Cliente cliente) {
        return repositorio
                .findByCliente(cliente)
                .map(e -> ResponseEntity.ok(e))
                .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public void criarProntuario(Medico medico, Cliente cliente) {
        Prontuario prontuario = ProntuarioRepositorio.instanciar(medico, cliente);
        repositorio.save(prontuario);
    }

    @Override
    public void adicionarConsultaAoProntuario(Cliente cliente, Consulta consulta) {
        Prontuario prontuario = buscarProntuarioPorCliente(cliente).getBody();
        prontuario.adicionarConsultaAoProntuario(consulta);
        repositorio.saveAndFlush(prontuario);
    }

    @Override
    public List<Prontuario> buscarProntuariosPorMedico(Medico medico) {
        return repositorio.buscarProntuariosPorMedico(medico);
    }

}
