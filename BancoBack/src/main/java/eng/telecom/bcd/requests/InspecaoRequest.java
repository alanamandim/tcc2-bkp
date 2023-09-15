package eng.telecom.bcd.requests;

import lombok.Data;

@Data
public class InspecaoRequest {
    private boolean ole;
    private boolean pneu;
    private boolean agua;
    private boolean amassado;
    private boolean arranhado;
    private String tanque;
    private String obs;
    private Integer idSolicitacao;

}
