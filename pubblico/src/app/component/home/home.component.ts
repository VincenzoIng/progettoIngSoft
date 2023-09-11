import {Component, OnInit} from '@angular/core';
import {StudentiService} from "../../service/studenti.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  esami: any[] = [];

  constructor(private studentiService: StudentiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getEsami();
  }

  public getEsami(){
    this.studentiService.mostraEsami().subscribe((resp => { this.esami = resp; }))
  }

  prenotaEsame(idesame:any){
    let matricola = prompt("Inserire la matricola:");
    let cf = prompt("Inserire codice fiscale:");
    this.studentiService.prenotaEsame(idesame, cf, matricola).subscribe((resp => { alert(resp); }))
  }

  prenotazioniStudente(){
    this.router.navigate(['/mostraPrenotazioni']);
  }

  risultatiStudente(){
    let cf = prompt("Inserire codice fiscale:");
    this.router.navigate(["/risultatiEsame", cf]);
  }

}
