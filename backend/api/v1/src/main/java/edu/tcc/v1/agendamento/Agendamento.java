package edu.tcc.v1.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.facade.ConversorDataHora;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_AGENDA_MEDICA")
@Getter
@Setter
@NoArgsConstructor
public class Agendamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataDisponivel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    Agendamento(CadastrarAgendamentoDTO dto, Medico medico) {
        this.dataDisponivel = ConversorDataHora.conversorDataHora(dto.dataDisponivel());
        this.medico = medico;
    }

}
