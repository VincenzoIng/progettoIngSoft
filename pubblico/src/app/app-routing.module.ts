import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {PrenotazioniComponent} from "./component/prenotazioni/prenotazioni.component";
import {SvolgiesameComponent} from "./component/svolgiesame/svolgiesame.component";
import {RisultatiComponent} from "./component/risultati/risultati.component";

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'mostraPrenotazioni', component:PrenotazioniComponent},
  {path: 'iniziaEsame/:id/:cf', component:SvolgiesameComponent},
  {path: 'risultatiEsame/:cf', component:RisultatiComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
