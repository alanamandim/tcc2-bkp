package eng.telecom.bcd.requests;

import lombok.Data;

@Data
public class ReservaRequest {
    private String motivo;
    private String dataHrInicio;
    private String dataHrFim;
    private int viatura;
    private String motorista;
}
