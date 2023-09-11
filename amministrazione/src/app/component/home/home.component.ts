import {Component, OnInit} from '@angular/core';
import {EsamiService} from "../../service/esami.service";
import {Esame} from "../../_model/esame.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  esami: any[] = [];

  constructor(private esamiService: EsamiService,
              private router:Router) {
  }

  ngOnInit(): void {
    this.getTuttiEsami();
  }

  public getTuttiEsami(){
    this.esamiService.mostraEsami().subscribe((resp: Esame[]) =>
    {
      console.log(resp);
      this.esami = resp;
    })
  }

  public aggiungiEsame(){
    this.router.navigate(['/aggiungiEsame']);
  }

  modificaEsame(idEsame:any){
    console.log(idEsame);
    this.router.navigate(['/modificaEsame', {idEsame: idEsame}]);
  }

}
