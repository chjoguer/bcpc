import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MovimientosRoutingModule } from './movimientos-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MovimientosApiService } from './services/movimientos-api.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; // Import ng-bootstrap module


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MovimientosRoutingModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [
    MovimientosApiService
    ]
})
export class MovimientosModule { }
