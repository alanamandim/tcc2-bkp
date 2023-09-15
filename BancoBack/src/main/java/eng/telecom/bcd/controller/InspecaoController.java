package eng.telecom.bcd.controller;

import eng.telecom.bcd.entities.Inspecao;
import eng.telecom.bcd.entities.RegistroDeMissao;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusMissao;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.enums.Tanque;
import eng.telecom.bcd.repository.InspecaoRepository;
import eng.telecom.bcd.repository.RegistroMissaoRepository;
import eng.telecom.bcd.repository.SolicitacaoRepository;
import eng.telecom.bcd.requests.InspecaoRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Slf4j
@RestController
public class InspecaoController {
    @Autowired
    @NonNull InspecaoRepository inspecaoRepository;

    @Autowired
    @NonNull RegistroMissaoRepository registroMissaoRepository;

    @Autowired
    @NonNull SolicitacaoRepository solicitacaoRepository;

    @PostMapping("/adicionaInspecao")
    public ResponseEntity<Object> setInpecao(@RequestBody InspecaoRequest inspecaoRequest){

        var inspecao = new Inspecao();
        inspecao.setAguaRadiador(inspecao.isAguaRadiador());
        inspecao.setOleo(inspecao.isOleo());
        inspecao.setAmassado(inspecaoRequest.isAmassado());
        inspecao.setAranhado(inspecao.isAranhado());
        inspecao.setTanque(Tanque.valueOf(inspecaoRequest.getTanque()));
        inspecao.setObservacao(inspecao.getObservacao());


        var solicitação = solicitacaoRepository.findById(inspecaoRequest.getIdSolicitacao());
        if(solicitação.isPresent()) {
            inspecaoRepository.save(inspecao);
            var s = solicitação.get();
            s.getViatura().setStatus(Status.EmMissao);
            var registro = new RegistroDeMissao();
            registro.setInspecao(inspecao);
            registro.setAprovador(s.getAprovador());
            registro.setKmInicial(s.getViatura().getHodometro());
            registro.setStatusMissao(StatusMissao.Iniciada);
            registro.setSolicitacao(s);
            registroMissaoRepository.save(registro);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inspeção realizada com sucesso \n Status da missão: "+ registro.getStatusMissao());
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Não foi possível realizar a inspeção");





    }

}
