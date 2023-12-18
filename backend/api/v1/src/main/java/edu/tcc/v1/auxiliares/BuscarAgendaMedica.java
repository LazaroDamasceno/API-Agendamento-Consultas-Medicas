package edu.tcc.v1.auxiliares;


import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaRepositorio;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarAgendaMedica {

    private final AgendaMedicaRepositorio repositorio;

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
