package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProntuarioServicoImpl implements ProntuarioServico {

    static ProntuarioRepositorio repositorio;

    public static ResponseEntity<Prontuario> buscarProntuarioPorCliente(Medico medico, Cliente cliente) {
        Optional<Prontuario> prontuario = repositorio.findByCliente(cliente);
        if (prontuario.isEmpty() || !prontuario.get().getMedico().equals(medico)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prontuario.get());
    }

    @Override
    public ResponseEntity<Void> criarProntuario(Medico medico, Cliente cliente) {
        Prontuario prontuario = ProntuarioRepositorio.instanciar(medico, cliente);
        repositorio.save(prontuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> adicionarConsultaAoProntuario(Medico medico, Cliente cliente, Consulta consulta) {
        Prontuario prontuario = buscarProntuarioPorCliente(medico, cliente).getBody();
        if (prontuario == null) return ResponseEntity.badRequest().build();
        prontuario.adicionarConsultaAoProntuario(consulta);
        repositorio.saveAndFlush(prontuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Prontuario>> buscarProntuariosPorMedico(Medico medico) {
        return new ResponseEntity<>(repositorio.buscarProntuariosPorMedico(medico), HttpStatus.OK);
    }

}
