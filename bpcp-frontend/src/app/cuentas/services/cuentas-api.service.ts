import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { accountDTO } from '../dto/accountDTO';
import { environment } from '../../../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class CuentasApiService {
 private readonly API_URL = `${environment.accountUrl}`; // Assuming you have an environment file

  
  constructor(private http: HttpClient) { }

  

  getAccounts(search?: string): Observable<any>{
   
    return this.http.get<accountDTO[]>(this.API_URL).pipe(
      catchError(this.handleError)
    );
  }

  

  getAccountById(search?: string): Observable<accountDTO> {
    return this.http.get<accountDTO>(`${this.API_URL}/${search}`).pipe(
      catchError(this.handleError)
    );
  }

  createAccount(client: any): Observable<accountDTO> {
    return this.http.post<accountDTO>(this.API_URL, client).pipe(
      catchError(this.handleError)
    );
  }

  updateAccount(id: number, account: accountDTO): Observable<accountDTO> {
    return this.http.put<accountDTO>(`${this.API_URL}/${id}`, account).pipe(
      catchError(this.handleError)
    );
  }

  deleteAccount(id: any): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Error handling
  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }
}
