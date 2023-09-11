import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {AggiungiesameComponent} from "./component/aggiungiesame/aggiungiesame.component";
import {ModificaesameComponent} from "./component/modificaesame/modificaesame.component";
import {EsamiResolveService} from "./service/esami-resolve.service";

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'aggiungiEsame', component:AggiungiesameComponent},
  {path:'modificaEsame', component:ModificaesameComponent, resolve :{ esame : EsamiResolveService}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
