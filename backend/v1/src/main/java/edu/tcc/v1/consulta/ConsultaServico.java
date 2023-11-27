package edu.tcc.v1.consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaServico {

    void cadastrarConsulta(AgendarConsulta dto);
    void cancelarConsulta(LocalDateTime dataAgendamento);
    Consulta exibirConsultaPelaDataDeAgendamento(LocalDateTime dataAgendamento);
    List<Consulta> exibirTodasAsConsultas();
    List<Consulta> exibirConsultasAgendadas();
    List<Consulta> exibirConsultasCanceladas();
    List<Consulta> exibirConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> exibirConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> exibirConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> exibirConsultasPeloNomeDoMedico(String nomeMedico);
    List<Consulta> exibirConsultasPeloNomeDoCliente(String nomeCliente);
    List<Consulta> exibirConsultasAgendadasPeloNomeDoCliente(String nomeCliente);
    List<Consulta> exibirConsultasCanceladasPeloNomeDoCliente(String nomeCliente);
    List<Consulta> exibirConsultasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Consulta> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);

}
