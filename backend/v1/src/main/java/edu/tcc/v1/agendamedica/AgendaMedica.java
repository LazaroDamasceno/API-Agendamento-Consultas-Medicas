package edu.tcc.v1.agendamedica;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_AGENDA_MEDICA")
@Getter
@Setter
@NoArgsConstructor
public class AgendaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private LocalDateTime dataDisponivel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    AgendaMedica(CadastrarAgendaMedicaDTO dto) {
        this.dataDisponivel = dto.dataDisponivel();
    }

}
