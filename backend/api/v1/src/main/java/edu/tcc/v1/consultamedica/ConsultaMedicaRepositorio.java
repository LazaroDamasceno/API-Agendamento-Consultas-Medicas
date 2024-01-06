package edu.tcc.v1.consultamedica;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsultaMedicaRepositorio extends JpaRepository<ConsultaMedica, UUID> {

    static ConsultaMedica instanciar(AgendarConsultaMedicaDTO dto, Cliente cliente, Medico medico) {
        return new ConsultaMedica(dto, cliente, medico);
    }

    @Query("""
        select c from Consulta c
    """)
    List<ConsultaMedica> buscarConsultasMedicas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
    """)
    List<ConsultaMedica> buscarConsultasMedicasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                             @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataCancelamento is null
    """)
    List<ConsultaMedica> buscarConsultasMedicasAgendadas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is null
    """)
    List<ConsultaMedica> buscarConsultasMedicasAgendadasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                      @Param("dataFinal") LocalDateTime dataFinal);

    @Query("""
        select c from Consulta c
        where c.dataCancelamento is not null
    """)
    List<ConsultaMedica> buscarConsultasMedicasCanceladas();

    @Query("""
        select c from Consulta c
        where c.dataAgendamento >= :dataInicial
        and c.dataAgendamento <= :dataFinal
        and c.dataCancelamento is not null
    """)
    List<ConsultaMedica> buscarConsultasMedicasCanceladasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                       @Param("dataFinal") LocalDateTime dataFinal);

}
