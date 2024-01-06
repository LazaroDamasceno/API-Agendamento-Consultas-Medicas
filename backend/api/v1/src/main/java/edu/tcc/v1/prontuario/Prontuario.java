package edu.tcc.v1.prontuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.consultamedica.ConsultaMedica;
import edu.tcc.v1.medico.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "V1_PRONTUARIO")
@Getter
@Setter
@NoArgsConstructor
public class Prontuario  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultas_id")
    private List<ConsultaMedica> consultas = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    Prontuario(Medico medico, Cliente cliente) {
        this.dataCriacao = LocalDateTime.now();
        this.medico = medico;
        this.cliente = cliente;
    }

    public void adicionarConsultaMedicaAoProntuario(ConsultaMedica consultaMedica) {
        consultas.add(consultaMedica);
    }

}
