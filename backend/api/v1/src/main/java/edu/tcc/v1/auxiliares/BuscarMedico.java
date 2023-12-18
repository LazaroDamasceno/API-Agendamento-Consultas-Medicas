package edu.tcc.v1.auxiliares;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.medico.MedicoRepositorio;

@Service
@AllArgsConstructor
public class BuscarMedico {

    private final MedicoRepositorio repositorio;

    public ResponseEntity<Medico> buscar(String crm) {
        return repositorio
                .findByCrm(crm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
