package com.server.service;

import com.google.protobuf.Empty;
import com.progetto.*;
import com.server.entity.Domanda;
import com.server.entity.EsameDB;
import com.server.entity.RisposteConsigliate;
import com.server.repository.DomandeRepository;
import com.server.repository.EsameDBRepository;
import com.server.repository.RisposteConsigliateRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;


@GrpcService
@AllArgsConstructor
public class ServerEsamiService extends EsameServiceGrpc.EsameServiceImplBase {

    EsameDBRepository esameDBRepository;
    DomandeRepository domandeRepository;
    RisposteConsigliateRepository risposteConsigliateRepository;

    @Override
    public void aggiungiEsame(EsameRequest request, StreamObserver<Esame> responseObserver) {
        EsameDB esameDB = new EsameDB();
        esameDB.setProfessore(request.getProfessore());
        esameDB.setMateria(request.getMateria());
        esameDB.setData(request.getData());
        esameDB.setRisposte(request.getRisposteList());
        esameDBRepository.save(esameDB);
        Esame esame = Esame.newBuilder()
                .setId(esameDB.getId())
                .setProfessore(request.getProfessore())
                .setMateria(request.getMateria())
                .setData(request.getData())
                .build();
        List<String> domandeRequest = request.getDomandeList();
        List<String> risposteConsigliateRequest = request.getRisposteConsigliateList();
        for(String d : domandeRequest){
            Domanda domanda = new Domanda();
            domanda.setIdesame(esameDB.getId());
            domanda.setTesto(d);
            domandeRepository.save(domanda);
        }
        for(String r : risposteConsigliateRequest){
            RisposteConsigliate risposte = new RisposteConsigliate();
            risposte.setIdesame(esameDB.getId());
            risposte.setRisposte(r);
            risposteConsigliateRepository.save(risposte);
        }
        responseObserver.onNext(esame);
        responseObserver.onCompleted();
    }


    @Override
    public void modificaEsame(Esame request, StreamObserver<Esame> responseObserver) {
        EsameDB esame = esameDBRepository.findById(request.getId()).get();
        esame.setProfessore(request.getProfessore());
        esame.setMateria(request.getMateria());
        esame.setData(request.getData());
        esameDBRepository.save(esame);
        Esame mod = Esame.newBuilder()
                .setId(esame.getId())
                .setProfessore(esame.getProfessore())
                .setMateria(esame.getMateria())
                .setData(esame.getData())
                .build();
        responseObserver.onNext(mod);
        responseObserver.onCompleted();
    }

    @Override
    public void getTuttiEsame(Empty request, StreamObserver<tuttiEsamiResponse> responseObserver) {
        List<EsameDB> esamiDB = esameDBRepository.findAll();
        List<Esame> esami = new ArrayList<>();
        for(EsameDB ex : esamiDB){
            Esame esame = Esame.newBuilder()
                    .setId(ex.getId())
                    .setProfessore(ex.getProfessore())
                    .setMateria(ex.getMateria())
                    .setData(ex.getData())
                    .build();
            esami.add(esame);
        }
        tuttiEsamiResponse resp = tuttiEsamiResponse.newBuilder()
                .addAllEsami(esami)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void dettagliEsame(idEsame request, StreamObserver<Esame> responseObserver) {
        EsameDB esameDB = esameDBRepository.findById(request.getId()).get();
        Esame esame = Esame.newBuilder()
                .setId(esameDB.getId())
                .setProfessore(esameDB.getProfessore())
                .setMateria(esameDB.getMateria())
                .setData(esameDB.getData())
                .build();
        responseObserver.onNext(esame);
        responseObserver.onCompleted();
    }
}
