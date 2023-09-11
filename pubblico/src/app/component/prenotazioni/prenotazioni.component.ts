import {Component, OnInit} from '@angular/core';
import {StudentiService} from "../../service/studenti.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-prenotazioni',
  templateUrl: './prenotazioni.component.html',
  styleUrls: ['./prenotazioni.component.css']
})
export class PrenotazioniComponent implements OnInit {

  prenotazioni: any = [];

  cf: any = "";

  constructor(private studentiService: StudentiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.cf = prompt("Inserisci il Codice Fiscale");
    this.getPrenotazioni(this.cf);
  }

  public getPrenotazioni(cf: any){
    this.studentiService.mostraPrenotazioni(cf).subscribe((resp => {this.prenotazioni = resp;} ))
  }

  cancellaPrenotazione(id:any){
    this.studentiService.cancellaPrenotazione(id).subscribe((resp => { alert(resp); this.getPrenotazioni(this.cf)}))
  }

  iniziaEsame(id: any){
    this.router.navigate(["/iniziaEsame", id, this.cf]);
  }

}
