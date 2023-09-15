package eng.telecom.bcd.controller;
import eng.telecom.bcd.entities.Reserva;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusMissao;
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

@Slf4j
@RestController
public class RegistroMissaoController {
    @Autowired
    @NonNull RegistroMissaoRepository registroMissaoRepository;

    @Autowired
    @NonNull InspecaoRepository inspecaoRepository;

    @Autowired
    @NonNull ViaturaRepository viaturaRepository;

    @Autowired
    @NonNull SolicitacaoRepository solicitacaoRepository;

    @Autowired
    @NonNull UsuarioRepository usuarioRepository;


//    @Transactional
//    @PutMapping("/validacaoSaida")
//    public ResponseEntity<Object> alteraLimite(@RequestBody AprovadorSolicitacaoResquest aprovadorSolicitacaoResquest) {
//        var usuario = usuarioRepository.findById(aprovadorSolicitacaoResquest.getAprovador());
//
//        var solicitação = solicitacaoRepository.findById(aprovadorSolicitacaoResquest.getId());
//        if(usuario.isPresent() && solicitação.isPresent()){
//            var s = solicitação.get();
//            s.setAprovador(usuario.get());
//            s.setStatusSolicitacao(StatusSolicitacao.valueOf(aprovadorSolicitacaoResquest.getStatus()));
//            return ResponseEntity.status(HttpStatus.OK).body("Solicitação: "+ aprovadorSolicitacaoResquest.getStatus());
//
//        }
//        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("NÃO Ok");
//
//    }
@Transactional
@PutMapping("/fechaFichaMotora")
public ResponseEntity<Object> fechaFichaMotora(@RequestBody FinalizaRequest finalizaRequest) {
    var registro = registroMissaoRepository.findById(finalizaRequest.getId());
    if(registro.isPresent()){
        var r = registro.get();
        r.setKmFinal(finalizaRequest.getKmFinal());
        r.getInspecao().setObservacao(finalizaRequest.getObs());
        r.getSolicitacao().getViatura().setHodometro(finalizaRequest.getKmFinal());
        return ResponseEntity.status(HttpStatus.OK).body("Ficha aguardando assinatura do aprovador");

    }
    return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("NÃO Ok");

}
    @Transactional
    @PutMapping("/fechaFichaAprovador")
    public ResponseEntity<Object> finalizaRegistro(@RequestBody AprovadorSolicitacaoResquest aprovadorSolicitacaoResquest) {


    var registro = registroMissaoRepository.findById(aprovadorSolicitacaoResquest.getId());
    if(registro.isPresent()){
            var r = registro.get();
            var u = usuarioRepository.findById(aprovadorSolicitacaoResquest.getAprovador());
        r.setAprovador(u.get());
        r.setStatusMissao(StatusMissao.Finalizada);
        r.getSolicitacao().getViatura().setStatus(Status.Disponivel);
        return ResponseEntity.status(HttpStatus.OK).body("Ficha finalizada");

        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("NÃO Ok");

    }

}
