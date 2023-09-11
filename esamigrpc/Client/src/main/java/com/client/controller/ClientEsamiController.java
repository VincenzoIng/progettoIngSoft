package com.client.controller;

import com.client.service.ClientEsamiService;
import com.client.support.EsameResponseSupp;
import com.client.support.EsameSupp;
import com.client.support.RichiestaAggiuntaEsame;
import com.progetto.Esame;
import com.progetto.EsameRequest;
import com.progetto.idEsame;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@AllArgsConstructor
public class ClientEsamiController {

    ClientEsamiService clientEsamiService;

    @PostMapping("/aggiungiEsame")
    @CrossOrigin("http://localhost:4200")
    public EsameResponseSupp aggiungiEsame(@RequestBody RichiestaAggiuntaEsame richiestaAggiuntaEsame) {
        EsameSupp esameSupp = richiestaAggiuntaEsame.getEsameSupp();
        EsameRequest esameRequest = EsameRequest.newBuilder()
                .setProfessore(esameSupp.getProfessore())
                .setMateria(esameSupp.getMateria())
                .setData(esameSupp.getData())
                .addAllDomande(richiestaAggiuntaEsame.getDomande())
                .addAllRisposte(richiestaAggiuntaEsame.getRisposte())
                .build();
        return clientEsamiService.aggiungiEsame(esameRequest);
    }


    @PostMapping("/modificaEsame")
    @CrossOrigin("http://localhost:4200")
    public EsameResponseSupp modificaEsame(@RequestBody EsameResponseSupp esameSupp){
        Esame esame = Esame.newBuilder()
                .setId(esameSupp.getId())
                .setProfessore(esameSupp.getProfessore())
                .setMateria(esameSupp.getMateria())
                .setData(esameSupp.getData())
                .build();
        return clientEsamiService.modificaEsame(esame);
    }

    @GetMapping("/getTuttiEsami")
    @CrossOrigin("http://localhost:4200")
    public ArrayList<EsameResponseSupp> getTuttiEsami() {
        return clientEsamiService.getTuttiEsami();
    }

    @GetMapping("/getDettagliEsame/{id}")
    @CrossOrigin("http://localhost:4200")
    public EsameResponseSupp getDettagliEsame(@PathVariable("id") Integer id){
        idEsame isex = idEsame.newBuilder()
                .setId(id)
                .build();
        return clientEsamiService.getDettagliEsame(isex);
    }


}
