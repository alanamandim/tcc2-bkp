package eng.telecom.bcd.entities;

import eng.telecom.bcd.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"viatura", "registroDeMissao"})
@Entity
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @NonNull
    @Column(nullable = false)
    private String motivo;

    @NonNull
    @Column(nullable = false)
    private Timestamp dataHrInicio;

    @NonNull
    @Column(nullable = false)
    private Timestamp dataHrFim;

    @NonNull
    @Column(nullable = false)
    private StatusSolicitacao statusSolicitacao;

    @NonNull
    @ManyToOne
    @JoinColumn (name = "viatura", nullable = false)
    private Viatura viatura;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "motorista", nullable = false)
    private Usuario motorista;

}
