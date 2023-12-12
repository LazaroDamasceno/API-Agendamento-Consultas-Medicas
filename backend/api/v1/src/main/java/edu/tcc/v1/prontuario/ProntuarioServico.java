package edu.tcc.v1.prontuario;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;

import java.time.LocalDateTime;
import java.util.List;

public interface ProntuarioServico {

    void cadastrarProntuario(String cpf);
    Prontuario exibirProntuarioPeloCliente(String cpf);
    List<Prontuario> exibirProntuariosEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Prontuario> exibirTodosOsProntuarios();
    void adicionarConsulta(String cpf, Consulta consulta);
    void associarMedico(String cpf, Medico medico);

}
