import { Injectable } from '@angular/core';
import {EsamiService} from "./esami.service";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Esame} from "../_model/esame.model";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EsamiResolveService implements Resolve<Esame>{

  constructor(private esamiService: EsamiService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Esame> {
    const id = route.paramMap.get("idEsame");
    if(id){
      return this.esamiService.dettagliEsame(id);
    } else {
      return of(this.getDettagliEsame());
    }
  }

  getDettagliEsame(){
    return {
      id: 0,
      professore: "",
      materia: "",
      data: ""
    }
  }


}
