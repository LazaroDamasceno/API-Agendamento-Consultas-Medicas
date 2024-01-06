package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.facade.Facade;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProntuarioServicoImpl implements ProntuarioServico {

    private ProntuarioRepositorio repositorio;
    private Facade facade;

    @Override
    public ResponseEntity<Void> criarProntuario(Medico medico, Cliente cliente) {
        Prontuario prontuario = ProntuarioRepositorio.instanciar(medico, cliente);
        repositorio.save(prontuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> adicionarConsultaMedicaAoProntuario(Medico medico, Cliente cliente, ConsultaMedica consultaMedica) {
        Prontuario prontuario = facade.buscarProntuarioPorCliente(medico, cliente).getBody();
        if (prontuario == null) return ResponseEntity.badRequest().build();
        prontuario.adicionarConsultaMedicaAoProntuario(consultaMedica);
        repositorio.saveAndFlush(prontuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Prontuario>> buscarProntuariosPorMedico(Medico medico) {
        return new ResponseEntity<>(repositorio.buscarProntuariosPorMedico(medico), HttpStatus.OK);
    }

}
