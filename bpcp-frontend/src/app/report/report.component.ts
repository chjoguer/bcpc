import { Component } from '@angular/core';
import { MovimientosApiService } from '../movimientos/services/movimientos-api.service';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { reportDTO } from './dto/reportDTO';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TableComponent } from '../shared/table/table.component';

@Component({
  selector: 'app-report',
  imports: [CommonModule,FormsModule,TableComponent],  // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent {

       reports: reportDTO[] = [];
       searchTerm$ = new Subject<string>();
       loading = false;
       error = '';
       successMessage = '';
       submitted = false;
       errorMessage = '';

       startDate: string = '';
       endDate: string = '';
       numberAccount: string = '';

       tableColumns = [
        { key: 'movementAt', title: 'Fecha' },
        { key: 'name', title: 'Cliente' },
        { key: 'numberAccount', title: '# Cuenta' },
        { key: 'typeAccount', title: 'Tipo Cuenta	' },
        { key: 'initialAmount', title: 'Saldo Inicial	' },
        { key: 'status', title: 'Status' },
        { key: 'movementAmount', title: 'Movimiento' },
        { key: 'totalAmount', title: 'Saldo Disponible' },
      ];

    
      tableData = [];

       
    
      constructor(public movementService: MovimientosApiService) {
      
    
      };
      ngOnInit(): void {
        this.loadReport();

        this.searchTerm$.pipe(
              debounceTime(300),
              distinctUntilChanged()
              
            ).subscribe(term => {
              console.log('Search term:', term);
              if(term.length == 0){
                this.loadReport();
              }
              this.reports = [];
              this.loadReport();
            });
      }

    loadReportByDate(): void {
      if (!this.startDate || !this.endDate) {
        alert("Por favor, selecciona un rango de fechas.");
        return;
      }
      if (!this.numberAccount) {
        alert("Por favor, ingrese un numero de cuenta.");
        return;
      }

      

      this.movementService.fetchMovementsReport(this.numberAccount, this.startDate, this.endDate, 'json').subscribe({
        next: (response) => {
          console.log('Response received:', response); // Debug log
          this.reports = response.data;
          this.tableData = response.data;

          this.loading = false;
        },
        error: (error) => {
          console.error('Error fetching movements:', error);
          this.error = 'Error loading movements. Please try again later.';
          this.loading = false;
        },
        complete: () => {
          this.loading = false;
        }
      });
    }

    onSearch(event: Event): void {
      const inputElement = event.target as HTMLInputElement;
      if (inputElement) {
        const term = inputElement.value.trim();
        this.searchTerm$.next(term);
      }
    }
  

      
    loadReport(): void {
      this.loading = true;
      this.error = '';
  
      this.movementService.fetchMovementsReport('683053', '2025-02-01', '2025-02-03', 'json').subscribe({
        next: (response) => {
          console.log('Response received:', response); // Debug log
          this.reports = response.data;
          this.loading = false;
        },
        error: (error) => {
          console.error('Error fetching movements:', error);
          this.error = 'Error loading movements. Please try again later.';
          this.loading = false;
        },
        complete: () => {
          this.loading = false;
        }
      });
    }

    downloadReportPdf(): void {
      if (!this.startDate || !this.endDate) {
        alert("Por favor, selecciona un rango de fechas.");
        return;
      }
     
      if (!this.numberAccount) {
        alert("Por favor, ingrese un numero de cuenta.");
        return;
      }
      this.movementService.fetchMovementsReport(this.numberAccount, this.startDate, this.endDate, 'pdf')
      .subscribe(base64String => {
        this.displayBase64PDF(base64String.data);
      }, error => {
        console.error("Error fetching PDF:", error);
      });


    }


    displayBase64PDF(base64: string) {
      const byteCharacters = atob(base64);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      const blob = new Blob([byteArray], { type: 'application/pdf' });

      const pdfUrl = URL.createObjectURL(blob);
      window.open(pdfUrl); // Open the PDF in a new tab
    }

}
