package edu.tcc.v1.agendamento;

import edu.tcc.v1.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepositorio extends JpaRepository<Agendamento, UUID> {

    static Agendamento instanciar(CadastrarAgendamentoDTO dto, Medico medico) {
        return new Agendamento(dto, medico);
    }

    @Query("""
        select am from AgendaMedica am
        where am.medico = :medico
    """)
    List<Agendamento> buscarAgendasMedicas(@Param("medico") Medico medico);

    @Query("""
        select am from AgendaMedica am
        where am.medico = :medico
        and am.dataDisponivel >= :dataInicial
        and am.dataDisponivel <= :dataFinal
    """)
    List<Agendamento> buscarAgendasMedicasEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                      @Param("dataFinal") LocalDateTime dataFinal,
                                                      @Param("medico") Medico medico);

}
