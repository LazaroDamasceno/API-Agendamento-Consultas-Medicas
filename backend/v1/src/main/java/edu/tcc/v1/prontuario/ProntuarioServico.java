package edu.tcc.v1.prontuario;

import edu.tcc.v1.consulta.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface ProntuarioServico {

    void atualizar(Prontuario prontuario);
    void cadastrarProntuario(String cpf);
    Prontuario exibirProntuarioPeloCliente(String cpf);
    List<Prontuario> exibirProntuariosEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    void adicionarConsulta(String cpf, Consulta consulta);

}
