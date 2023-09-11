import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import {HttpClientModule} from "@angular/common/http";
import { AggiungiesameComponent } from './component/aggiungiesame/aggiungiesame.component';
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { ModificaesameComponent } from './component/modificaesame/modificaesame.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AggiungiesameComponent,
    ModificaesameComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
