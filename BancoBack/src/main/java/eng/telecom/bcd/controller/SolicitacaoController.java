package eng.telecom.bcd.controller;

import eng.telecom.bcd.entities.Inspecao;
import eng.telecom.bcd.entities.Solicitacao;
import eng.telecom.bcd.entities.Usuario;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.repository.SolicitacaoRepository;
import eng.telecom.bcd.repository.UsuarioRepository;
import eng.telecom.bcd.repository.ViaturaRepository;
import eng.telecom.bcd.requests.AprovadorSolicitacaoResquest;
import eng.telecom.bcd.requests.InspecaoRequest;
import eng.telecom.bcd.requests.SolicitacaoResquest;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@Slf4j
@RestController
public class SolicitacaoController {
    @Autowired
    @NonNull SolicitacaoRepository solicitacaoRepository;
    @Autowired
    @NonNull UsuarioRepository usuarioRepository;
    @Autowired
    @NonNull ViaturaRepository viaturaRepository;

    @PostMapping("/adicionaSolicitacao")
    public ResponseEntity<Object> setSolicitacao(@RequestBody SolicitacaoResquest solicitacaoResquest) {
        var data = new Timestamp(System.currentTimeMillis());
        var usuario = usuarioRepository.findById(solicitacaoResquest.getMotorista());
        var viatura = viaturaRepository.findById(solicitacaoResquest.getViatura());
        if (usuario.isPresent() && viatura.isPresent()) {
            var solicitacao = new Solicitacao(data, StatusSolicitacao.AguardandoAProvacao, solicitacaoResquest.getMotivo(), solicitacaoResquest.getDestino(), viatura.get(), usuario.get());
            //         var solicitacao1 = new Solicitacao(data,StatusSolicitacao.AguardandoAProvacao,solicitacaoResquest.getMotivo(), solicitacaoResquest.getDestino(), viatura.get(),usuario.get());
            solicitacaoRepository.save(solicitacao);
            return ResponseEntity.status(HttpStatus.OK).body("Solicitacao Criada"+ solicitacao.getIdSolicitacao());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível abrir a solicitação");
    }

    @Transactional
    @PutMapping("/modificaStatusSolicitacao") //modifica limite do dependente OK
    public ResponseEntity<Object> alteraLimite(@RequestBody AprovadorSolicitacaoResquest aprovadorSolicitacaoResquest) {
        var usuario = usuarioRepository.findById(aprovadorSolicitacaoResquest.getAprovador());

        var solicitação = solicitacaoRepository.findById(aprovadorSolicitacaoResquest.getId());
        if(usuario.isPresent() && solicitação.isPresent()){
            var s = solicitação.get();
            s.setAprovador(usuario.get());
            s.setStatusSolicitacao(StatusSolicitacao.valueOf(aprovadorSolicitacaoResquest.getStatus()));
            return ResponseEntity.status(HttpStatus.OK).body("Solicitação: "+ aprovadorSolicitacaoResquest.getStatus());

        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("NÃO Ok");

    }

    @GetMapping("/listaSolicitacaoAprovada")
    public  ResponseEntity<List<Object[]>>procuraSolicitacaoAprovada(){

       return ResponseEntity.ok().body(solicitacaoRepository.listaSolicitacaoAprovada());
    }

    @GetMapping("/listaSolicitacaoAguardandoAprovacao")
    public  ResponseEntity<List<Object[]>>procuraSolicitacaoAgdAprov(){

        return ResponseEntity.ok().body(solicitacaoRepository.listaSolicitacaoAguardandoAprovacao());
    }





}
