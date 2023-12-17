package edu.tcc.v1.agendamedica;

import edu.tcc.v1.medico.Medico;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class BuscarAgendaMedica {

    AgendaMedicaRepositorio repositorio;

    public ResponseEntity<AgendaMedica> buscar(LocalDateTime dataDisponivel, Medico medico) {
        Optional<AgendaMedica> agendaMedica = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataDisponivel().equals(dataDisponivel)
                                && e.getMedico().equals(medico)
                ).findFirst();
        return agendaMedica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
