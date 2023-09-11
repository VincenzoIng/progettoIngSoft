import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Esame} from "../_model/esame.model";
import {Prenotazione} from "../_model/prenotazione.model";

@Injectable({
  providedIn: 'root'
})
export class StudentiService {

  constructor(private httpClient: HttpClient) { }

  public mostraEsami(){
    return this.httpClient.get<Esame[]>("http://localhost:9090/getTuttiEsami");
  }

  public prenotaEsame(idesame: number, cf: any, matricola: any){
    return this.httpClient.post("http://localhost:9090/prenotazioneEsame", {idesame, cf, matricola}, {responseType: "text"});
  }

  public mostraPrenotazioni(cf: any){
    return this.httpClient.get<Prenotazione[]>("http://localhost:9090/getTuttePrenotazioni/"+cf);
  }

  public cancellaPrenotazione(id: any){
    return this.httpClient.delete("http://localhost:9090/cancellaPrenotazione/"+id, {responseType:"text"});
  }

  public getDomandeEsame(id: any){
    return this.httpClient.get<string[]>("http://localhost:9090/getDomandeEsame/"+id);
  }

  public inviaRisposte(id: any, cf: any, risposte: any[]){
    return this.httpClient.post("http://localhost:9090/inviaRisposte", {id, cf, risposte}, {responseType: "text"});
  }

  public getRisultati(cf: any){
    return this.httpClient.get<any[]>("http://localhost:9090/getRisultatiStudente/"+cf);
  }

}
