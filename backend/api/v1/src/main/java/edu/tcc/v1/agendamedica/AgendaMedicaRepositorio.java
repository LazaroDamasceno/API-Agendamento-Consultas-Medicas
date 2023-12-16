package edu.tcc.v1.agendamedica;

import edu.tcc.v1.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendaMedicaRepositorio extends JpaRepository<AgendaMedica, UUID> {

    static AgendaMedica instanciar(CadastrarAgendaMedicaDTO dto, Medico medico) {
        return new AgendaMedica(dto, medico);
    }

    @Query("""
        select am from AgendaMedica am
        where am.medico = :medico
    """)
    List<AgendaMedica> buscarAgendasMedicas(@Param("medico") Medico medico);

    @Query("""
        select am from AgendaMedica am
        where am.medico = :medico
        and am.dataDisponivel >= :dataInicial
        and am.dataDisponivel <= :dataFinal
    """)
    List<AgendaMedica> buscarAgendasMedicasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                      @Param("dataFinal") LocalDateTime dataFinal,
                                                      @Param("medico") Medico medico);

}
