import {Component, OnInit} from '@angular/core';
import {Esame} from "../../_model/esame.model";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {EsamiService} from "../../service/esami.service";

@Component({
  selector: 'app-modificaesame',
  templateUrl: './modificaesame.component.html',
  styleUrls: ['./modificaesame.component.css']
})
export class ModificaesameComponent implements OnInit {

  esame: Esame = {
    id: 0,
    professore: '',
    materia: '',
    data: ''
  }

  ngOnInit(): void {
    this.esame = this.activatedRouter.snapshot.data['esame'];
  }

  constructor(private router:Router,
              private activatedRouter: ActivatedRoute,
              private esamiService: EsamiService) {
  }

  modificaEsame(esameForm: NgForm){
    this.esamiService.modificaDettagliEsame(esameForm.value).subscribe(
      resp => { console.log(resp); this.router.navigate(['']);}
    );
  }

}
