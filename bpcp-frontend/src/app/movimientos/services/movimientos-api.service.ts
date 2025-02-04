import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { movementDTO } from '../dto/movementDTO';
import { environment } from '../../../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class MovimientosApiService {

 private readonly API_URL = `${environment.movementUrl}`; 
 private readonly API_URL_REPORT = `${environment.movementUrlReport}`; 

   
   constructor(private http: HttpClient) { }
 
   
 
   getMovements(accountNumber?: string): Observable<movementDTO[]> {
     let params = new HttpParams();
     if (accountNumber) {
       return this.http.get<movementDTO[]>(`${this.API_URL}/${accountNumber}`,).pipe(
        catchError(this.handleError)
      );
     }
 
     return this.http.get<movementDTO[]>(this.API_URL, { params }).pipe(
       catchError(this.handleError)
     );
   }

     createMovement(movement: any): Observable<movementDTO> {
       return this.http.post<movementDTO>(this.API_URL, movement).pipe(
         catchError(this.handleError)
       );
     }

     fetchMovementsReport(
      accountNumber: string, 
      startDate: string, 
      endDate: string, 
      exportType: string = 'json'
    ): Observable<any> {
      let params = new HttpParams()
        .set('startDate', startDate)
        .set('endDate', endDate)
        .set('exportType', exportType); // "base64" or "json"
  
      return this.http.get<any>(`${this.API_URL_REPORT}/${accountNumber}/report`, { params }).pipe(
        catchError(this.handleError)
      );
    }
 
   
 
   // Error handling
   private handleError(error: any) {
     console.error('An error occurred:', error);
     return throwError(() => new Error('Something went wrong; please try again later.'));
   }
}
