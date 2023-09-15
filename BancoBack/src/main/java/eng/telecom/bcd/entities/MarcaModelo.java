package eng.telecom.bcd.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@EqualsAndHashCode( exclude = {"viaturas"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString( exclude = {"viaturas"})
@Entity
public class MarcaModelo implements Serializable {
    @NonNull
    @Column(nullable = false)
    private String marca;

    @Id
    private String modelo;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.EAGER)
    private Set<Viatura> viaturas = new HashSet<>();


}
