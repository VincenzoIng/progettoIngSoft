package com.clientstud.service;

import com.clientstud.support.EsameResponseSupp;
import com.clientstud.support.PrenotazioniDBSupp;
import com.clientstud.support.RisultatiSupp;
import com.google.protobuf.Empty;
import com.progetto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientStudentiService {

    ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9000)
            .usePlaintext()
            .build();

    StudentiServiceGrpc.StudentiServiceBlockingStub stub = StudentiServiceGrpc.newBlockingStub(channel);

    public String prenotaAdEsame(prenotazioneRequest pRequest) {
        prenotazioneResponse resp = stub.prenotazioneEsame(pRequest);
        return resp.getEsito();
    }

    public String cancellaPrenotazione(cancellaRequest request){
        cancellazioneRespose response = stub.cancellaPrenotazione(request);
        return response.getEsito();
    }

    public String inviaRisposte(risposteRequest request){
        risposteResponse resp = stub.inviaRisposte(request);
        return "Punteggio totale di: " + resp.getPunteggio() + " " + resp.getMat();
    }

    public ArrayList<EsameResponseSupp> getTuttiEsami() {
        tuttiEsamiResponseStu resp = stub.getTuttiEsame(Empty.newBuilder().build());
        List<EsameStu> esamiResp = resp.getEsamiList();
        ArrayList<EsameResponseSupp> esami = new ArrayList<>();
        for(EsameStu e : esamiResp){
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

    public ArrayList<PrenotazioniDBSupp> getTuttePrenotazioni(tuttePrenotazioniRequest request){
        tuttePrenotazioniResponse resp = stub.getTuttePrenotazioni(request);
        List<prenotazione> prenotazioniResp = resp.getPrenotazioniList();
        ArrayList<PrenotazioniDBSupp> prenotazioni = new ArrayList<>();
        for(prenotazione p : prenotazioniResp){
            PrenotazioniDBSupp prenotazione = new PrenotazioniDBSupp.Builder()
                    .id(p.getId())
                    .idesame(p.getIdesame())
                    .materiaesame(p.getMateriaesame())
                    .cf(p.getCf())
                    .matricola(p.getMatricola())
                    .build();
            prenotazioni.add(prenotazione);
        }
        return prenotazioni;
    }

    public List<String> getDomandeEsame(domandeRequest request){
        domandeResponse resp = stub.getDomandeEsame(request);
        return resp.getDomandeList();
    }

    public List<String> getRisposteConsigliate(consigliateRequest request){
        consigliateResponse resp = stub.getRisposteSuggerite(request);
        return resp.getSuggeriteList();
    }

    public ArrayList<RisultatiSupp> getRisultatiStudente(tuttePrenotazioniRequest request){
        risultatiStudenteResponse resp = stub.getRisultatiStudente(request);
        List<risultatiStudente> ris = resp.getRisultatifinList();
        ArrayList<RisultatiSupp> risultati = new ArrayList<>();
        for(risultatiStudente risultato : ris){
            RisultatiSupp target = new RisultatiSupp.Builder()
                    .id(risultato.getIdesame())
                    .nomeprofessore(risultato.getNomeprofessore())
                    .nomeesame(risultato.getNomeesame())
                    .dataesame(risultato.getDataesame())
                    .matricola(risultato.getMatricola())
                    .punteggio(risultato.getPunteggio())
                    .build();
            risultati.add(target);
        }
        return risultati;
    }


}
