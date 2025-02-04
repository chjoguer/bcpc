import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { MovimientosApiService } from '../movimientos/services/movimientos-api.service';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ReportRoutingModule,

  ],
  providers: [
      MovimientosApiService
  ]
})
export class ReportModule { }
