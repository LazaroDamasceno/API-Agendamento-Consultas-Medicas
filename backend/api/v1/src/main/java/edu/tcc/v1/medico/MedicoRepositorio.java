package edu.tcc.v1.medico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicoRepositorio extends JpaRepository<Medico, UUID> {

    Optional<Medico> findByCrm(String crm);

}
