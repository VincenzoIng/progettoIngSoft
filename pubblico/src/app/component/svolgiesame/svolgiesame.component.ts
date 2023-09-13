import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentiService} from "../../service/studenti.service";
import {interval, Subscription} from "rxjs";

@Component({
  selector: 'app-svolgiesame',
  templateUrl: './svolgiesame.component.html',
  styleUrls: ['./svolgiesame.component.css']
})
export class SvolgiesameComponent implements OnInit {

  id: number = 0;

  domande: string[] = [];

  risposteSuggerite: string[] = [];

  risposta: string = "";

  risposte: string[] = [];

  risposteFinali: string[] = [];

  fineesame: boolean = false;

  i: number = 0;

  tempoLimite: number = 300;

  tempoTrascorso: number = 0;

  tempoEsame: number = 0;

  tempoTotale: number = 3000;

  timerSubscription: Subscription | undefined;

  cf: string = "";

  constructor(private route: ActivatedRoute,
              private studentiService: StudentiService,
              private router: Router) {
  }

  ngOnInit(): void {

    this.route.paramMap.subscribe(param => {
      // @ts-ignore
      this.id = param.get('id');

      // @ts-ignore
      this.cf = param.get('cf');


    });

    this.studentiService.getRisposteConsigliate(this.id).subscribe((resp => { this.risposteSuggerite = resp; console.log(this.risposteSuggerite);}))
    this.studentiService.getDomandeEsame(this.id).subscribe((resp => { this.domande = resp; console.log(this.domande);}))
    this.iniziaEsame();
  }

  iniziaEsame(): void {
    this.timerSubscription = interval(1000).subscribe(() => {
      this.tempoTrascorso++;
      this.tempoEsame++;
      if (this.tempoTrascorso >= this.tempoLimite) {
        this.aggiungiRisposta();
      }
      if(this.tempoEsame >= this.tempoTotale){
        while(this.i < this.domande.length) {
          this.aggiungiRisposta2();
          this.i = this.i + 1;
        }
        this.terminaEsame()
      }

    });
  }

  aggiungiRisposta2(){
    this.risposte.push(this.risposta);
    console.log(this.risposta);
    console.log(this.risposte);
    this.risposta = "";
  }

  aggiungiRisposta() {
    if (!this.fineesame) {
      this.risposte.push(this.risposta);
      console.log(this.risposta);
      console.log(this.risposte);
      this.risposta = "";
      this.i = this.i + 1;
      this.tempoTrascorso = 0;

      if (this.i === this.domande.length) {
        this.terminaEsame();
      }
    }
  }

  terminaEsame(){
    this.fineesame = true;
    this.risposteFinali = this.risposte;
  }

  inviaRisposte(){
    console.log(this.risposteFinali);
    return this.studentiService.inviaRisposte(this.id, this.cf, this.risposteFinali).subscribe(
      (resp => {alert(resp); this.router.navigate(['/risultatiEsame', this.cf]); } ))
  }

}
