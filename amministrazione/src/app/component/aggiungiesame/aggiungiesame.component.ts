import { Component } from '@angular/core';
import {Esame} from "../../_model/esame.model";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {EsamiService} from "../../service/esami.service";

@Component({
  selector: 'app-aggiungiesame',
  templateUrl: './aggiungiesame.component.html',
  styleUrls: ['./aggiungiesame.component.css']
})
export class AggiungiesameComponent {

  domanda: string = "";
  domande: string[] = []

  risposta: string = "";
  risposte : string[] = [];

  rispostaConsigliata: string = "";
  risposteConsigliate: string[] = [];

  constructor(private router:Router,
              private esameService: EsamiService) {
  }

  aggiungiRisposta(){
    this.risposte.push(this.risposta);
    this.risposta = "";
  }

  aggiungiDomanda(){
    this.domande.push(this.domanda);
    this.domanda = "";
  }

  aggiungiRispostaConsigliata(){
    this.risposteConsigliate.push(this.rispostaConsigliata);
    this.rispostaConsigliata = "";
  }

  aggiungiEsame(esameForm: NgForm){
    console.log(this.risposteConsigliate);
    this.esameService.aggiungiEsame(esameForm.value, this.risposte, this.domande, this.risposteConsigliate).subscribe(
      resp => { console.log(resp); esameForm.reset(); this.router.navigate(['']); }
    );
  }
}
