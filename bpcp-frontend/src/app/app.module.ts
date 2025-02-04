import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms'; // Import here

import { AppComponent } from './app.component';
import { ClientesComponent } from './clientes/clientes.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent, // Your component using formGroup
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule, // Add ReactiveFormsModule here
    RouterModule.forRoot([]) // Import RouterModule and configure routes

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
