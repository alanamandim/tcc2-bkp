package eng.telecom.bcd.controller;

import eng.telecom.bcd.entities.Reserva;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.repository.*;
import eng.telecom.bcd.requests.*;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Slf4j
@RestController
public class ReservaController {
    @Autowired
    @NonNull UsuarioRepository usuarioRepository;

    @Autowired
    @NonNull ReservaRepository reservaRepository;

    @Autowired
    @NonNull ViaturaRepository viaturaRepository;

    @PostMapping("/adicionaReserva")
    public ResponseEntity<Object> setReserva(@RequestBody ReservaRequest reservaRequest) {
        var usuario = usuarioRepository.findById(reservaRequest.getMotorista());
        var viatura = viaturaRepository.findById(reservaRequest.getViatura());
        if (usuario.isPresent() && viatura.isPresent()) {
            var reserva = new Reserva(reservaRequest.getMotivo(), Timestamp.valueOf(reservaRequest.getDataHrInicio()),Timestamp.valueOf(reservaRequest.getDataHrFim()),StatusSolicitacao.AguardandoAProvacao,viatura.get(),usuario.get());
            //         var solicitacao1 = new Solicitacao(data,StatusSolicitacao.AguardandoAProvacao,solicitacaoResquest.getMotivo(), solicitacaoResquest.getDestino(), viatura.get(),usuario.get());
            reservaRepository.save(reserva);
            return ResponseEntity.status(HttpStatus.OK).body("Solicitacao de reserva Criada"+ reserva.getIdReserva());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível abrir a solicitação de reserva");
    }

    @Transactional
    @PutMapping("/modificaStatusReserva")
    public ResponseEntity<Object> alteraLimite(@RequestBody AprovadorReservaRequest aprovadorReservaRequest) {
        var reserva = reservaRepository.findById(aprovadorReservaRequest.getId());
        if(reserva.isPresent()){
            var r = reserva.get();
            r.setStatusSolicitacao(StatusSolicitacao.valueOf(aprovadorReservaRequest.getStatus()));
            return ResponseEntity.status(HttpStatus.OK).body("Reserva: "+ aprovadorReservaRequest.getStatus());

        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("NÃO Ok");

    }

}
