package edu.tcc.v1.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ConsultaRepositorio extends JpaRepository<Consulta, UUID> {

    Optional<Consulta> findByDataAgendamento(LocalDateTime dataAgendamento);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento is null
    """)
    List<Consulta> exibirConsultasAgendadas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento is not null
    """)
    List<Consulta> exibirConsultasCanceladas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
    """)
    List<Consulta> exibirConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
    """)
    List<Consulta> exibirConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
    """)
    List<Consulta> exibirConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.medico.usuario.nome = :nomeMedico
    """)
    List<Consulta> exibirConsultasPeloNomeDoMedico(String nomeMedico);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasPeloNomeDoCliente(String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
        and c.dataCancelamento is null
    """)
    List<Consulta> exibirConsultasAgendadasPeloNomeDoCliente(String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
        and c.dataCancelamento is not null
    """)
    List<Consulta> exibirConsultasCanceladasPeloNomeDoCliente(String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal);

}
