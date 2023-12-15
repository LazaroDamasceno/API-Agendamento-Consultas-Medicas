package edu.tcc.v1.prontuario;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProntuarioRepositorio extends JpaRepository<Prontuario, UUID> {

    Optional<Prontuario> findByCliente(Cliente cliente);

    @Query("""
        select p from Prontuario p
        where p.medico = :medico
    """)
    List<Prontuario> buscarProntuariosPorMedico(@Param("medico") Medico medico);

    static Prontuario instanciar(Medico medico, Cliente cliente) {
        return new Prontuario(medico, cliente);
    }

}
