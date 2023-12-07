package edu.tcc.v1.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.conversor.dataHora.ConversorDataHora;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_CONSULTA")
@Getter
@Setter
@NoArgsConstructor
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime dataAgendamento;

    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime dataCancelamento;

    private String observacoesMedicas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    Consulta(AgendarConsultaDTO dto) {
        this.dataAgendamento = ConversorDataHora.conversor(dto.dataAgendamento());
    }

}
