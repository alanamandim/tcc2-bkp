package eng.telecom.bcd.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AprovadorSolicitacaoResquest {
    String aprovador;
    String status;
    Integer id;
}
