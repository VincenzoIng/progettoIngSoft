import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {StudentiService} from "../../service/studenti.service";

@Component({
  selector: 'app-risultati',
  templateUrl: './risultati.component.html',
  styleUrls: ['./risultati.component.css']
})
export class RisultatiComponent implements OnInit {

  cf: any;

  risultati: any[] = [];

  constructor(private route: ActivatedRoute,
              private studentiService: StudentiService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe( param => { this.cf = param.get('cf'); })
    console.log(this.cf);
    this.studentiService.getRisultati(this.cf).subscribe((resp => { this.risultati = resp; console.log(this.risultati); }))
  }



}
