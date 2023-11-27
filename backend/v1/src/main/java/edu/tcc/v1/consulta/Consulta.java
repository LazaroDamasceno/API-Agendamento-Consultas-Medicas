package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_CONSULTA")
@Getter
@Setter
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private LocalDateTime dataAgendamento;

    private LocalDateTime dataCancelamento;

    @Column(nullable = false)
    private String observacoesMedicas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    Consulta(AgendarConsulta dto) {
        this.dataAgendamento = dto.dataAgendamento();
    }

}
