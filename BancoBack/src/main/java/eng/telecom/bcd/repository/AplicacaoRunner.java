package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.*;
import eng.telecom.bcd.enums.Status;
import eng.telecom.bcd.enums.StatusMissao;
import eng.telecom.bcd.enums.StatusSolicitacao;
import eng.telecom.bcd.enums.Tanque;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.providers.entertainment.SouthPark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Slf4j
@RequiredArgsConstructor
public class AplicacaoRunner implements CommandLineRunner {
    @Autowired
    @NonNull ViaturaRepository viaturaRepository;

    @Autowired
    @NonNull MarcaModeloRepository marcaModeloRepository;

    @Autowired
    @NonNull SolicitacaoRepository solicitacaoRepository;

    @Autowired
    @NonNull RegistroMissaoRepository registroMissaoRepository;

    @Autowired
    @NonNull ReservaRepository reservaRepository;

    @Autowired
    @NonNull InspecaoRepository inspecaoRepository;

    @Autowired
    @NonNull UsuarioRepository usuarioRepository;

    private void povoarbanco(){
        var modelo = new MarcaModelo();
        var marca = "Fiat";
        var mod = "Doblo";
        modelo.setModelo(mod);
        modelo.setMarca(marca);
        marcaModeloRepository.save(modelo);

        var viatura  =  new Viatura("123e44","gasolina",54276, Status.Disponivel,modelo);
        viaturaRepository.save(viatura);

        var usuario = new Usuario("6791212","alana","12345","alana@gmail.com",true,true,false,false,true);
        usuarioRepository.save(usuario);
        var usuario2 = new Usuario("6783452","motoristaaa","23456","motora@gmail.com",true, false,false,false,false);
        usuarioRepository.save(usuario2);
        var solicitacao = new Solicitacao();
        var data = new Timestamp(System.currentTimeMillis());
        solicitacao.setDatahr(data);
        solicitacao.setStatusSolicitacao(StatusSolicitacao.AguardandoAProvacao);
        solicitacao.setDestino("BAFL");
        solicitacao.setMotivoMissao("Abastecer");
        solicitacao.setViatura(viatura);
        solicitacao.setMotorista(usuario2);
        //solicitacao.setAprovador(usuario);
        solicitacaoRepository.save(solicitacao);

        solicitacao.setStatusSolicitacao(StatusSolicitacao.Aprovada);
        solicitacao.setAprovador(usuario);
        solicitacaoRepository.save(solicitacao);

        var inspecao = new Inspecao();
        inspecao.setAguaRadiador(true);
        inspecao.setOleo(true);
        inspecao.setAmassado(false);
        inspecao.setPneu(true);
        inspecao.setAranhado(false);
        inspecao.setTanque(Tanque.Cheio);
        inspecaoRepository.save(inspecao);


        var registro = new RegistroDeMissao();
        registro.setSolicitacao(solicitacao);
        registro.setInspecao(inspecao);
        registro.setKmInicial(viatura.getHodometro());
        registro.setStatusMissao(StatusMissao.Iniciada);
        registro.setAprovador(solicitacao.getAprovador());
        registroMissaoRepository.save(registro);

//        var reserva = new Reserva();
//        reserva.setViatura(viatura);
//        reserva.setMotorista(usuario2);
//        reserva.setStatusSolicitacao(StatusSolicitacao.AguardandoAProvacao);
//        reserva.setMotivo("Viagem RNF Manutencão");
//        reserva.setDataHrInicio("2023-07-31 16:22:11.811000");
//        reserva.setDataHrFim("2023-08-10 16:22:11.811000");
//        reservaRepository.save(reserva);


    }

    @Override
    public void run(String... args) throws Exception {
        try {
            povoarbanco();
//            do{
//
//
//            }while (true);
            //criar função para sempre verificar reserva e alterar status quando a mesma coincidir a data
            //criar função viatura reservada só ficar disponível para solicitação
            System.out.println(viaturaRepository.findById(1));
            System.out.println(marcaModeloRepository.findAll());
            System.out.println(solicitacaoRepository.findAll());
            System.out.println(registroMissaoRepository.findAll());
            System.out.println(reservaRepository.findAll());



            //    listarDados();
            //    System.out.println(dependenteRepository.findById("106.123.456-78"));


        } catch (Exception e) {
            log.error(e.toString());
        }

    }
}
