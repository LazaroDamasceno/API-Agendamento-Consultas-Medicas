package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaServico {

    public void atualizar(Consulta consulta);
    void associarCliente(LocalDateTime dataAgendamento, Cliente cliente);
    void associarMedico(LocalDateTime dataAgendamento, Medico medico);
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
