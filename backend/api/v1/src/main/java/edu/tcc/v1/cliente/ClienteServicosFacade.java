package edu.tcc.v1.cliente;

import org.springframework.stereotype.Service;

import edu.tcc.v1.agendamedica.AgendaMedicaServicoImpl;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import edu.tcc.v1.usuario.UsuarioServicoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class ClienteServicosFacade {

    private ClienteRepository repository;
    private UsuarioServicoImpl usuarioServico;
    private ConsultaServicoImpl consultaServico;
    private AgendaMedicaServicoImpl amServico;
    
}
