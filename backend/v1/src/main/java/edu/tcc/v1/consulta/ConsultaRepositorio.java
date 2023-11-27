package edu.tcc.v1.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<Consulta> exibirConsultasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                             @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
    """)
    List<Consulta> exibirConsultasAgendadasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                      @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
    """)
    List<Consulta> exibirConsultasCanceladasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                       @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.medico.usuario.nome = :nomeMedico
    """)
    List<Consulta> exibirConsultasPeloNomeDoMedico(@Param("nomeMedico") String nomeMedico);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
        and c.dataCancelamento is null
    """)
    List<Consulta> exibirConsultasAgendadasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.cliente.usuario.nome = :nomeCliente
        and c.dataCancelamento is not null
    """)
    List<Consulta> exibirConsultasCanceladasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasEntreDatasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente,
                                                              @Param("dataInicial") LocalDateTime dataInicial,
                                                              @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente,
                                                                       @Param("dataInicial") LocalDateTime dataInicial,
                                                                       @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
        and c.cliente.usuario.nome = :nomeCliente
    """)
    List<Consulta> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(@Param("nomeCliente") String nomeCliente,
                                                                        @Param("dataInicial") LocalDateTime dataInicial,
                                                                        @Param("dataFinal") LocalDateTime dataFinal);

}
