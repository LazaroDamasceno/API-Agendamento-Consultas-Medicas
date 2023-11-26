package edu.tcc.v1.medico;

import java.util.List;

public interface MedicoServico {

    Medico exibirMedicoPeloCRM(String crm);
    List<Medico> exibirTodosOsMedicos();
    void cadastrarMedico(CadastrarMedicoDTO dto);
    void demitirMedico(String crm);

}
