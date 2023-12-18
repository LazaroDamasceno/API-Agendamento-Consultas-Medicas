package edu.tcc.v1.auxiliares;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import edu.tcc.v1.agendamedica.AgendaMedica;
import edu.tcc.v1.agendamedica.AgendaMedicaRepositorio;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DesassociarConsulta {

    private final AuxliaresFacade auxliaresFacade;
    private final AgendaMedicaRepositorio repositorio;

    public void desassociar(LocalDateTime dataDisponivel, Medico medico) {
        AgendaMedica agendaMedica = auxliaresFacade.getAgendaMedica().buscar(dataDisponivel, medico).getBody();
        if (agendaMedica != null) {
            agendaMedica.setConsulta(null);
            repositorio.save(agendaMedica);
        }
    }
    
}
