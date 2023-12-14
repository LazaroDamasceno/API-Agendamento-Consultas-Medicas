package edu.tcc.v1.agendamedica;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.tcc.v1.auxiliares.ConversorDataHora;
import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_AGENDA_MEDICA")
@Getter
@Setter
@NoArgsConstructor
public class AgendaMedica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime dataDisponivel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    AgendaMedica(CadastrarAgendaMedicaDTO dto) {
        this.dataDisponivel = ConversorDataHora.conversorDataHora(dto.dataDisponivel());
    }

}
