import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { PrenotazioniComponent } from './component/prenotazioni/prenotazioni.component';
import { SvolgiesameComponent } from './component/svolgiesame/svolgiesame.component';
import { RisultatiComponent } from './component/risultati/risultati.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PrenotazioniComponent,
    SvolgiesameComponent,
    RisultatiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
