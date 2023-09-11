package com.server.service;

import com.google.protobuf.Empty;
import com.progetto.*;
import com.server.entity.*;
import com.server.repository.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;


@GrpcService
@AllArgsConstructor
public class ServerStudentiService extends StudentiServiceGrpc.StudentiServiceImplBase {

    EsameDBRepository esameDBRepository;
    PrenotazioneDBRepository prenotazioneDBRepository;
    StudenteDBRepository studenteDBRepository;
    RisultatiDBRepository risultatiDBRepository;
    DomandeRepository domandeRepository;

    @Override
    public void prenotazioneEsame(prenotazioneRequest request, StreamObserver<prenotazioneResponse> responseObserver) {
        EsameDB esameDB = esameDBRepository.findById(request.getId()).get();
        if(studenteDBRepository.findStudenteByMatricola(request.getMatr()) != null && !studenteDBRepository.findStudenteByMatricola(request.getMatr()).getCf().equals(request.getCf())){
            prenotazioneResponse resp = prenotazioneResponse.newBuilder().setEsito("La matricola inserita non è corretta, esiste già!").build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }
        if(!studenteDBRepository.existsById(request.getCf())){
            Studente s = new Studente();
            s.setCf(request.getCf());
            s.setMatricola(request.getMatr());
            studenteDBRepository.save(s);
        }
        Studente studente = studenteDBRepository.findById(request.getCf()).get();
        if(!studente.getMatricola().equals(request.getMatr())) {
            prenotazioneResponse resp = prenotazioneResponse.newBuilder().setEsito("La matricola inserita non è corretta!").build();
            responseObserver.onNext(resp);
        }
        else if(prenotazioneDBRepository.findByStudenteAndEsame(studente, esameDB) != null){
            prenotazioneResponse resp = prenotazioneResponse.newBuilder().setEsito("Impossibile effetturare prenotazione, studente già prenotato!").build();
            responseObserver.onNext(resp);
        }
        else {
            PrenotazioneDB prenotazione = new PrenotazioneDB();
            prenotazione.setEsame(esameDB);
            prenotazione.setStudente(studente);
            prenotazioneDBRepository.save(prenotazione);
            prenotazioneResponse resp = prenotazioneResponse.newBuilder().setEsito("Prenotazione effettuata con successo!").build();
            responseObserver.onNext(resp);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void cancellaPrenotazione(cancellaRequest request, StreamObserver<cancellazioneRespose> responseObserver) {
        PrenotazioneDB prenotazioneDB = prenotazioneDBRepository.findById(request.getId()).get();
        if(prenotazioneDBRepository.findById(request.getId()).isPresent()){
            prenotazioneDBRepository.delete(prenotazioneDB);
            cancellazioneRespose resp = cancellazioneRespose.newBuilder().setEsito("Cancellazione avvenuta con successo!").build();
            responseObserver.onNext(resp);
        }
        else {
            cancellazioneRespose resp = cancellazioneRespose.newBuilder().setEsito("Impossibile effettuare cancellazione!").build();
            responseObserver.onNext(resp);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void inviaRisposte(risposteRequest request, StreamObserver<risposteResponse> responseObserver) {
        EsameDB esame = esameDBRepository.findById(request.getId()).get();
        Studente studente = studenteDBRepository.findById(request.getCf()).get();
        List<String> risposteEsame = esame.getRisposte();
        List<String> risposteStudente = request.getRisposteList();
        int punteggio = calcolaPunteggio(risposteEsame, risposteStudente);
        Risultati risultato = new Risultati();
        risultato.setEsame(esame);
        risultato.setStudente(studente);
        risultato.setPunteggio(punteggio);
        risultatiDBRepository.save(risultato);
        risposteResponse resp = risposteResponse.newBuilder()
                .setPunteggio(punteggio)
                .setMat(studente.getMatricola())
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    private int calcolaPunteggio(List<String> rispEsame, List<String> rispStudente) {
        int punteggio = 0;
        for(int i = 0; i < rispEsame.size(); i++){
            if(rispStudente.get(i).equals(""))
                punteggio -= 1;
            else if(rispStudente.get(i).equals(rispEsame.get(i)))
                punteggio += 3;
            else
                punteggio += 0;
        }
        return punteggio;
    }

    @Override
    public void getTuttiEsame(Empty request, StreamObserver<tuttiEsamiResponseStu> responseObserver) {
        List<EsameDB> esamiDB = esameDBRepository.findAll();
        List<EsameStu> esami = new ArrayList<>();
        for(EsameDB ex : esamiDB){
            EsameStu esame = EsameStu.newBuilder()
                    .setId(ex.getId())
                    .setProfessore(ex.getProfessore())
                    .setMateria(ex.getMateria())
                    .setData(ex.getData())
                    .build();
            esami.add(esame);
        }
        tuttiEsamiResponseStu resp = tuttiEsamiResponseStu.newBuilder()
                .addAllEsami(esami)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void getTuttePrenotazioni(tuttePrenotazioniRequest request, StreamObserver<tuttePrenotazioniResponse> responseObserver) {
        Studente s = studenteDBRepository.findById(request.getCf()).get();
        List<PrenotazioneDB> prenotazioniStudente = prenotazioneDBRepository.findByStudente(s);
        List<prenotazione> prenotazioni = new ArrayList<>();
        for(PrenotazioneDB p : prenotazioniStudente) {
            prenotazione pr = prenotazione.newBuilder()
                    .setId(p.getId())
                    .setIdesame(p.getEsame().getId())
                    .setMateriaesame(p.getEsame().getMateria())
                    .setCf(p.getStudente().getCf())
                    .setMatricola(p.getStudente().getMatricola())
                    .build();
            prenotazioni.add(pr);
        }
        tuttePrenotazioniResponse resp = tuttePrenotazioniResponse.newBuilder()
                .addAllPrenotazioni(prenotazioni)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void getDomandeEsame(domandeRequest request, StreamObserver<domandeResponse> responseObserver) {
        List<Domanda> domandeDB = domandeRepository.findAllByIdesame(request.getId());
        List<String> domande = new ArrayList<>();
        for(Domanda d : domandeDB){
            domande.add(d.getTesto());
        }
        domandeResponse resp = domandeResponse.newBuilder()
                .addAllDomande(domande)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void getRisultatiStudente(tuttePrenotazioniRequest request, StreamObserver<risultatiStudenteResponse> responseObserver) {
        Studente studente = studenteDBRepository.findById(request.getCf()).get();
        List<Risultati> risultati = risultatiDBRepository.findAllByStudente(studente);
        List<risultatiStudente> ris = new ArrayList<>();
        for(Risultati r: risultati){
            risultatiStudente temp = risultatiStudente.newBuilder()
                    .setIdesame(r.getEsame().getId())
                    .setNomeesame(r.getEsame().getMateria())
                    .setNomeprofessore(r.getEsame().getProfessore())
                    .setDataesame(r.getEsame().getData())
                    .setMatricola(r.getStudente().getMatricola())
                    .setPunteggio(r.getPunteggio())
                    .build();
            ris.add(temp);
        }
        risultatiStudenteResponse resp = risultatiStudenteResponse.newBuilder()
                .addAllRisultatifin(ris)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }



}
