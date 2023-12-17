package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class BuscarProntuario {

    ProntuarioRepositorio repositorio;

    public ResponseEntity<Prontuario> buscarPorCliente(Medico medico, Cliente cliente) {
        Optional<Prontuario> prontuario = repositorio.findByCliente(cliente);
        if (prontuario.isEmpty() || !prontuario.get().getMedico().equals(medico)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prontuario.get());
    }

}
