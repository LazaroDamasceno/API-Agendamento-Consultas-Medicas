package edu.tcc.v1.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.tcc.v1.usuario.Usuario;
import edu.tcc.v1.usuario.UsuarioServicoImp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "V1_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String uf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastramento;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    Cliente(CadastrarClienteDTO dto) {
        this.cep = dto.cep();
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.dataCadastramento = LocalDateTime.now();
        this.usuario = UsuarioServicoImp.instanciar(dto.usuario());
    }

}
