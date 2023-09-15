package eng.telecom.bcd.requests;

import lombok.Data;

@Data
public class SolicitacaoResquest {
    private String motivo;
    private String destino;
    //private String status;
    private int viatura;
    private String motorista;
}
