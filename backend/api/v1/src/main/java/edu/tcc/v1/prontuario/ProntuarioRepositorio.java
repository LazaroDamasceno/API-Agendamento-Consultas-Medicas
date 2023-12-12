package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProntuarioRepositorio extends JpaRepository<Prontuario, UUID> {

    Optional<Prontuario> findByCliente(Cliente cliente);

    @Query("""
        select p from Prontuario p
        where p.dataCriacao >= :dataInicial
        and p.dataCriacao <= :dataFinal
    """)
    List<Prontuario> exibirProntuariosEntreDatas(@Param("dataInicial") LocalDateTime dataInicial,
                                                 @Param("dataFinal") LocalDateTime dataFinal);

}
