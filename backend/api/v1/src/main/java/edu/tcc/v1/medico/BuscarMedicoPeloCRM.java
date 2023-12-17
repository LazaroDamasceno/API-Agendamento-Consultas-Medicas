package edu.tcc.v1.medico;

import org.springframework.http.ResponseEntity;

public class BuscarMedicoPeloCRM {

    private MedicoRepositorio repositorio;

    public ResponseEntity<Medico> buscar(String crm) {
        return repositorio
                .findByCrm(crm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }


}
