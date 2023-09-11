import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Esame} from "../_model/esame.model";


@Injectable({
  providedIn: 'root'
})
export class EsamiService {


  constructor(private httpClient: HttpClient) { }

  public mostraEsami(){
    return this.httpClient.get<Esame[]>("http://localhost:8080/getTuttiEsami");
  }


  public aggiungiEsame(esameSupp:any, risposte: string[], domande: string[]){
    return this.httpClient.post("http://localhost:8080/aggiungiEsame", {esameSupp, risposte, domande});
  }

  public dettagliEsame(idEsame:any){
    return this.httpClient.get<Esame>("http://localhost:8080/getDettagliEsame/" + idEsame);
  }

  public modificaDettagliEsame(esameForm:any){
    return this.httpClient.post("http://localhost:8080/modificaEsame", esameForm);
  }


}
