package eng.telecom.bcd.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import eng.telecom.bcd.enums.StatusMissao;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@EqualsAndHashCode( exclude = {"solicitacao","inspecao","aprovador", "aprovadorFim"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"solicitacao","inspecao", "aprovador", "aprovadorFim"})
@Entity
public class RegistroDeMissao implements Serializable {
    @NonNull
    @Column(nullable = false)
    private Integer kmInicial;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistro;

//    @NonNull
//    @Column(nullable = false)
//    private Motorista motoristaSaída;

    @NonNull
    @Column
    private StatusMissao statusMissao;

    private Integer kmFinal;

    private Timestamp dataHrSaida;

    private Timestamp dataHrChegada;


    private String motoristaRetorno;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao solicitacao;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "aprovador", nullable = false)
    private Usuario aprovador;

    @ManyToOne
    @JoinColumn(name = "aprovadorFim",nullable = true)
    private Usuario aprovadorFim;







}
