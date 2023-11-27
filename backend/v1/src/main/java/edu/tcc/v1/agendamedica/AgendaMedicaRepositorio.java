package edu.tcc.v1.agendamedica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendaMedicaRepositorio extends JpaRepository<AgendaMedica, UUID> {

    Optional<AgendaMedica> findByDataDisponivel(LocalDateTime dataDisponivel);

    @Query("""
        select am from AgendaMedica am
        where am.dataDisponivel >= :dataInicial
        and am.dataDisponivel <= :dataFinal
    """)
    List<AgendaMedica> exibirAgendasMedicasEntreDatas(LocalDateTime dataIncial, LocalDateTime dataFinal);

}
