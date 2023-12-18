package edu.tcc.v1.auxiliares;


import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaRepositorio;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuscarConsulta {

    private final ConsultaRepositorio repositorio;

    public ResponseEntity<Consulta> buscarConsultaPeloMedico(LocalDateTime dataHora, Medico medico) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getMedico().equals(medico)
                ).findFirst();
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Consulta> buscarConsultaPeloCliente(LocalDateTime dataHora, Cliente cliente) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getCliente().equals(cliente)
                ).findFirst();
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
