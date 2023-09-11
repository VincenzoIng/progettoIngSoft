package com.client.service;

import com.client.support.EsameResponseSupp;
import com.google.protobuf.Empty;
import com.progetto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientEsamiService {


    ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9000)
            .usePlaintext()
            .build();
    EsameServiceGrpc.EsameServiceBlockingStub stub = EsameServiceGrpc.newBlockingStub(channel);

    public EsameResponseSupp aggiungiEsame(EsameRequest esameRequest) {
        Esame esame = stub.aggiungiEsame(esameRequest);
        return new EsameResponseSupp.Builder()
                .id(esame.getId())
                .professore(esame.getProfessore())
                .materia(esame.getMateria())
                .data(esame.getData())
                .build();
    }

    public EsameResponseSupp modificaEsame(Esame esame){
        Esame modificato = stub.modificaEsame(esame);
        return new EsameResponseSupp.Builder()
                .id(modificato.getId())
                .professore(modificato.getProfessore())
                .materia(modificato.getMateria())
                .data(modificato.getData())
                .build();
    }

    public ArrayList<EsameResponseSupp> getTuttiEsami() {
        tuttiEsamiResponse resp = stub.getTuttiEsame(Empty.newBuilder().build());
        List<Esame> esamiResp = resp.getEsamiList();
        ArrayList<EsameResponseSupp> esami = new ArrayList<>();
        for(Esame e : esamiResp){
            EsameResponseSupp esame = new EsameResponseSupp.Builder()
                    .id(e.getId())
                    .professore(e.getProfessore())
                    .materia(e.getMateria())
                    .data(e.getData())
                    .build();
            esami.add(esame);
        }
        return esami;
    }

    public EsameResponseSupp getDettagliEsame(idEsame id){
        Esame esame = stub.dettagliEsame(id);
        return new EsameResponseSupp.Builder()
                .id(esame.getId())
                .professore(esame.getProfessore())
                .materia(esame.getMateria())
                .data(esame.getData())
                .build();
    }

}
