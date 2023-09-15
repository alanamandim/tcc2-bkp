package eng.telecom.bcd.requests;
import lombok.Data;

@Data
public class FinalizaRequest {
    private int kmFinal;
    private String obs;
    private int id;
}
