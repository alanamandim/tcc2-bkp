package eng.telecom.bcd.entities;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.enums.Tanque;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"viatura", "registroDeMissao", "usuario"})
@Entity
public class Solicitacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitacao;

    @NonNull
    private Timestamp datahr;

    @NonNull
    @Column(nullable = false)
    private StatusSolicitacao statusSolicitacao;

    @NonNull
    @Column(nullable = false)
    private String motivoMissao;

    @NonNull
    @Column(nullable = false)
    private String destino;

    @NonNull
    @ManyToOne
    @JoinColumn (name = "viatura", nullable = false)
    private Viatura viatura;

    @OneToOne(mappedBy = "solicitacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RegistroDeMissao registroDeMissao;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "motorista", nullable = false)
    private Usuario motorista;

    @ManyToOne
    @JoinColumn(name = "aprovador",nullable = true)
    private Usuario aprovador;




}
