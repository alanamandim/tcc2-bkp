package eng.telecom.bcd.entities;

import jakarta.persistence.*;
import lombok.*;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode( exclude = {"solicitacoes", "registroDeMissao","solicitacoesApr", "registroDeMissaoApr","reservas"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"solicitacoes", "registroDeMissao","solicitacoesApr", "registroDeMissaoApr","reservas"})
@Entity
public class Usuario implements Serializable {
    @Id
    @NonNull
    private String saram;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String senha;
// String pass = new String(senha.getPassword());

    @NonNull
    @Column(nullable = false)
    private String email;

//    @NonNull
//    @Column
//    private BufferedImage foto;
    @NonNull
    @Column(nullable = false)
    private boolean motorista;

    @NonNull
    @Column(nullable = false)
    private boolean gestor;

    @NonNull
    @Column(nullable = false)
    private boolean identificador;

    @NonNull
    @Column(nullable = false)
    private boolean aprovador;

    @NonNull
    @Column(nullable = false)
    private boolean chefe;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.EAGER)
    private Set<Solicitacao> solicitacoes = new HashSet<>();

    @OneToMany(mappedBy = "aprovador", fetch = FetchType.EAGER)
    private Set<RegistroDeMissao> registroDeMissaos = new HashSet<>();

    @OneToMany(mappedBy = "aprovador", fetch = FetchType.EAGER)
    private Set<RegistroDeMissao> solicitacoesApr = new HashSet<>();

    @OneToMany(mappedBy = "aprovadorFim", fetch = FetchType.EAGER)
    private Set<RegistroDeMissao> registroDeMissaosApr = new HashSet<>();

    @OneToMany(mappedBy = "motorista", fetch = FetchType.EAGER)
    private Set<Reserva> reservas = new HashSet<>();


}
