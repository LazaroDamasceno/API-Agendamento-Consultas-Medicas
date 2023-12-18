package edu.tcc.v1.auxiliares;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class AuxliaresFacade {

    private final BuscarAgendaMedica agendaMedica;
    private final BuscarCliente cliente;
    private final BuscarConsulta consulta;
    private final BuscarMedico medico;
    private final BuscarProntuario prontuario;
    private final BuscarUsuario usuario;
    
}
