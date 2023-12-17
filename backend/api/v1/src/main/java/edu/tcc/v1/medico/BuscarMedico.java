package edu.tcc.v1.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuscarMedico {

    @Autowired
    MedicoRepositorio repositorio;

    public ResponseEntity<Medico> buscar(String crm) {
        return repositorio
                .findByCrm(crm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
