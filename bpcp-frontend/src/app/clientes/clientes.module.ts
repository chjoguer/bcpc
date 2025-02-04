import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientApiServiceService } from './services/client-api-service.service';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    ClientApiServiceService
  ]
})
export class ClientesModule { }
