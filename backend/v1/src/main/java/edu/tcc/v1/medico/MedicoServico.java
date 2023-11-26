package edu.tcc.v1.medico;

import java.util.List;

public interface MedicoServico {

    Medico exibirMedicoPeloCRM(String crm);
    List<Medico> exibirTodosOsMedicos();
    List<Medico> exibirTodosMedicosAtivos();
    List<Medico> exibirTodosMedicosDemitidos();
    void cadastrarMedico(CadastrarMedicoDTO dto);
    void demitirMedico(String crm);

}
