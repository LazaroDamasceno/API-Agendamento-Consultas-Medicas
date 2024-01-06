package edu.tcc.v1.consultamedica;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.facade.Facade;
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
@Table(name = "V1_CONSULTA")
@Getter
@Setter
@NoArgsConstructor
public class ConsultaMedica implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAgendamento;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCancelamento;

    private String observacoesMedicas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    ConsultaMedica(AgendarConsultaMedicaDTO dto, Cliente cliente, Medico medico) {
        this.dataAgendamento = Facade.conversorDataHora(dto.dataAgendamento());
        this.cliente = cliente;
        this.medico = medico;
    }

}
