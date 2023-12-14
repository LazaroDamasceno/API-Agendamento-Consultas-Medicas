package edu.tcc.v1.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsultaRepositorio extends JpaRepository<Consulta, UUID> {

    static Consulta instanciar(AgendarConsultaDTO dto) {
        return new Consulta(dto);
    }

    @Query("""
        select c from Consulta c
    """)
    List<Consulta> buscarTodasAsConsultas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
    """)
    List<Consulta> buscarConsultasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                             @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataCancelamento is null
    """)
    List<Consulta> buscarConsultasAgendadas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
    """)
    List<Consulta> buscarConsultasAgendadasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                      @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataCancelamento is not null
    """)
    List<Consulta> buscarConsultasCanceladas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
    """)
    List<Consulta> buscarConsultasCanceladasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                       @Param("dataFinal") LocalDateTime dataFinal);

}
