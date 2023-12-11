package edu.tcc.v1.medico;

import org.springframework.stereotype.Service;

import edu.tcc.v1.agendamedica.AgendaMedicaServicoImpl;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.prontuario.ProntuarioServicoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class MedicoServicosFacade {

    private MedicoRepositorio repositorio;
    private AgendaMedicaServicoImpl amServico;
    private ConsultaServicoImpl consultaServico;
    private ProntuarioServicoImpl prontuarioServico;
    
}
