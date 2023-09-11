package com.clientstud.controller;

import com.clientstud.service.ClientStudentiService;
import com.clientstud.support.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.progetto.prenotazioneRequest;
import com.progetto.cancellaRequest;
import com.progetto.risposteRequest;
import com.progetto.tuttePrenotazioniRequest;
import com.progetto.domandeRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClientStudentiController {

    ClientStudentiService clientStudentiService;

    @PostMapping("/prenotazioneEsame")
    @CrossOrigin("http://localhost:50750")
    public String prenotaAdEsame(@RequestBody PrenotazioneSupp prenotazioneSupp){
        prenotazioneRequest pRequest = prenotazioneRequest.newBuilder()
                .setId(prenotazioneSupp.getIdesame())
                .setCf(prenotazioneSupp.getCf())
                .setMatr(prenotazioneSupp.getMatricola())
                .build();
        return clientStudentiService.prenotaAdEsame(pRequest);
    }

    @DeleteMapping("/cancellaPrenotazione/{id}")
    @CrossOrigin("http://localhost:50750")
    public String cancellaPrenotazione(@PathVariable Integer id) {
        cancellaRequest req = cancellaRequest.newBuilder()
                .setId(id)
                .build();
        return clientStudentiService.cancellaPrenotazione(req);
    }

    @PostMapping("/inviaRisposte")
    @CrossOrigin("http://localhost:50750")
    public String inviaRisposte(@RequestBody RisposteStudenteSupp risposte) {
        risposteRequest request = risposteRequest.newBuilder()
                .setId(risposte.getId())
                .setCf(risposte.getCf())
                .addAllRisposte(risposte.getRisposte())
                .build();
        return clientStudentiService.inviaRisposte(request);
    }

    @GetMapping("/getTuttiEsami")
    @CrossOrigin("http://localhost:50750")
    public ArrayList<EsameResponseSupp> getTuttiEsami() {
        return clientStudentiService.getTuttiEsami();
    }


    @GetMapping("/getTuttePrenotazioni/{cf}")
    @CrossOrigin("http://localhost:50750")
    public ArrayList<PrenotazioniDBSupp> getTuttePrenotazioni(@PathVariable String cf) {
        tuttePrenotazioniRequest request = tuttePrenotazioniRequest.newBuilder()
                .setCf(cf)
                .build();
        return clientStudentiService.getTuttePrenotazioni(request);
    }

    @GetMapping("/getDomandeEsame/{id}")
    @CrossOrigin("http://localhost:50750")
    public List<String> getDomandeEsame(@PathVariable Integer id){
        domandeRequest request = domandeRequest.newBuilder()
                .setId(id)
                .build();
        return clientStudentiService.getDomandeEsame(request);
    }

    @GetMapping("/getRisultatiStudente/{cf}")
    @CrossOrigin("http://localhost:50750")
    public ArrayList<RisultatiSupp> getRisultatiStudente(@PathVariable String cf){
        tuttePrenotazioniRequest request = tuttePrenotazioniRequest.newBuilder()
                .setCf(cf)
                .build();
        return clientStudentiService.getRisultatiStudente(request);
    }

}
