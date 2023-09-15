package eng.telecom.bcd.entities;


import eng.telecom.bcd.enums.Tanque;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@ToString ( exclude = {"registroDeMissao"})
@Entity
public class Inspecao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_inspecao;

    @NonNull
    @Column(nullable = false)
    private boolean oleo;

    @NonNull
    @Column(nullable = false)
    private boolean pneu;

    @NonNull
    @Column(nullable = false)
    private boolean aguaRadiador;

    @NonNull
    @Column(nullable = false)
    private boolean amassado;

    @NonNull
    @Column(nullable = false)
    private boolean aranhado;

    @NonNull
    @Column(nullable = false)
    private Tanque tanque;

    private String observacao;

    @OneToOne(mappedBy = "inspecao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RegistroDeMissao registroDeMissao;
}
