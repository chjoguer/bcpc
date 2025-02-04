import { Routes } from '@angular/router';
import { ClientesComponent } from './clientes/clientes.component';
import { CuentasComponent } from './cuentas/cuentas.component';
import { MovimientosComponent } from './movimientos/movimientos.component';
import { ReportComponent } from './report/report.component';

export const routes: Routes = [
  { path: 'clientes', component: ClientesComponent },
  { path: 'cuentas', component: CuentasComponent },
  { path: 'movimientos', component: MovimientosComponent },
  { path: 'reportes',component: ReportComponent},
  { path: '', redirectTo: '/cuentas', pathMatch: 'full' },
];
