package edu.tcc.v1.medico;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServicoImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_MEDICO")
@Getter
@Setter
@NoArgsConstructor
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime dataAdmissao;

    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime dataDemissao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    Medico(CadastrarMedicoDTO dto) {
        this.dataAdmissao = LocalDateTime.now();
        this.crm = dto.crm();
        this.cnpj = dto.cnpj();
        this.usuario = UsuarioServicoImpl.instanciar(dto.usuario());
    }

}
