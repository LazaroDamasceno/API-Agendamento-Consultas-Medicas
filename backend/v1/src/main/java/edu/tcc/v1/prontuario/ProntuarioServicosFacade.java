package edu.tcc.v1.prontuario;

import org.springframework.stereotype.Service;

import edu.tcc.v1.cliente.ClienteServicoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class ProntuarioServicosFacade {
    
    private ProntuarioRepositorio repositorio;
    private ClienteServicoImpl clienteServico;

}
