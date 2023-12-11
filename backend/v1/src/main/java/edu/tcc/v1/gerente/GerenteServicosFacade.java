package edu.tcc.v1.gerente;

import org.springframework.stereotype.Service;

import edu.tcc.v1.cliente.ClienteServicoImpl;
import edu.tcc.v1.medico.MedicoServicoImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class GerenteServicosFacade {

    private MedicoServicoImpl medicoServico;
    private ClienteServicoImpl clienteServico;
    
}
