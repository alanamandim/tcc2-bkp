package eng.telecom.bcd.entities;

import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.Tanque;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode ( exclude = {"solicitacoes", "modelo","reservas"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"solicitacoes", "modelo","reservas"})
@Entity
public class Viatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_viatura;

    @NonNull
    @Column(nullable = false)
    private String chassi;

    @NonNull
    @Column(nullable = false)
    private String tipoCombustivel;

    @NonNull
    @Column
    private Integer hodometro;

    @NonNull
    @Column(nullable = false)
    private Status status;

    @NonNull
    @ManyToOne
    @JoinColumn (name = "modelo", nullable = false)
    private MarcaModelo modelo;

    @OneToMany(mappedBy = "viatura", fetch = FetchType.EAGER)
    private Set<Solicitacao> solicitacoes = new HashSet<>();

    @OneToMany(mappedBy = "viatura", fetch = FetchType.EAGER)
    private Set<Reserva> reservas = new HashSet<>();

}
