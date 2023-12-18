package edu.tcc.v1.auxiliares;


import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.prontuario.Prontuario;
import edu.tcc.v1.prontuario.ProntuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarProntuario {

    private final ProntuarioRepositorio repositorio;

    public ResponseEntity<Prontuario> buscarPorCliente(Medico medico, Cliente cliente) {
        Optional<Prontuario> prontuario = repositorio.findByCliente(cliente);
        if (prontuario.isEmpty() || !prontuario.get().getMedico().equals(medico)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prontuario.get());
    }

}
