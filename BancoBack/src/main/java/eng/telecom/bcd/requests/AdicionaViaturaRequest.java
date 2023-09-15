package eng.telecom.bcd.requests;
import eng.telecom.bcd.entities.MarcaModelo;
import eng.telecom.bcd.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class AdicionaViaturaRequest {
    private String chassi;
    private String tipoCombustivel;
    private Integer hodometro;
    private String modelo;
}
