import { Component } from '@angular/core';
import { MovimientosApiService } from '../movimientos/services/movimientos-api.service';
import { Subject } from 'rxjs';
import { reportDTO } from './dto/reportDTO';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-report',
  imports: [CommonModule,FormsModule],  // Import CommonModule to use *ngIf, *ngFor, etc.
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

       
    
      constructor(public movementService: MovimientosApiService) {
      
    
      };
      ngOnInit(): void {
        this.loadReport();
      }

    loadReportByDate(): void {
      if (!this.startDate || !this.endDate) {
        alert("Por favor, selecciona un rango de fechas.");
        return;
      }

      this.movementService.fetchMovementsReport('683053', this.startDate, this.endDate, 'json').subscribe({
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
      this.movementService.fetchMovementsReport('683053', this.startDate, this.endDate, 'pdf')
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
