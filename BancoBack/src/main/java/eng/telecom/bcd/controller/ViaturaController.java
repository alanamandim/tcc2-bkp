package eng.telecom.bcd.controller;


import eng.telecom.bcd.entities.*;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.repository.MarcaModeloRepository;
import eng.telecom.bcd.repository.SolicitacaoRepository;
import eng.telecom.bcd.repository.UsuarioRepository;
import eng.telecom.bcd.repository.ViaturaRepository;
import eng.telecom.bcd.requests.AdicionaViaturaRequest;
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
public class ViaturaController {
    @Autowired
    @NonNull ViaturaRepository viaturaRepository;

    @Autowired
    @NonNull MarcaModeloRepository marcaModeloRepository;

    @GetMapping("/listaSituacaoViaturas")
    public  ResponseEntity<List<Object[]>>verificaViatura(){

        return ResponseEntity.ok().body(viaturaRepository.listaviaturas());
    }

    @GetMapping("/listaViaturasDisponiveis")
    public  ResponseEntity<List<Object[]>>verificaViaturaDisponivel(){

        return ResponseEntity.ok().body(viaturaRepository.listaviaturasDisponiveis());
    }

    @GetMapping("/listaViaturasFora")
    public  ResponseEntity<List<Object[]>>verificaViaturaFora(){

        return ResponseEntity.ok().body(viaturaRepository.listaviaturasFora());
    }
    @GetMapping("/listaViaturasReservada")
    public  ResponseEntity<List<Object[]>>verificaViaturasReservadas(){

        return ResponseEntity.ok().body(viaturaRepository.listaviaturasReservadas());
    }

    @PostMapping("/adicionaViatura")
    public ResponseEntity<Object> setSolicitacao(@RequestBody AdicionaViaturaRequest adicionaViaturaRequest) {
        var marc = marcaModeloRepository.findById(adicionaViaturaRequest.getModelo());

        if (marc.isPresent()) {
            var viatura = new Viatura(adicionaViaturaRequest.getChassi(), adicionaViaturaRequest.getTipoCombustivel(), adicionaViaturaRequest.getHodometro(), Status.Disponivel,marc.get());
            viaturaRepository.save(viatura);
            return ResponseEntity.status(HttpStatus.OK).body("Viatura adicionada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível abrir adicionar");
    }




}
