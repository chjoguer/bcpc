import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { ClientDTO, CreateClientDTO, UpdateClientDTO } from '../dto/clientDTO';

@Injectable({
  providedIn: 'root'
})
export class ClientApiServiceService {

  // private readonly API_URL = `${environment.apiUrl}/clients`; // Assuming you have an environment file
  private readonly API_URL = `http://localhost:8080/bff/customers`; // Assuming you have an environment file

  
  constructor(private http: HttpClient) { }

  // Get all clients
  // getClients(): Observable<any> {
  //   console.log('Fetching clients from:', `${this.API_URL}`); // Debug log
  //   return this.http.get(`${this.API_URL}`);
    
  // }
  getClients(search?: string): Observable<ClientDTO[]> {
    if (search) {
      return this.http.get<ClientDTO[]>(`${this.API_URL}/${search}`).pipe(
        catchError(this.handleError)
      );    
    }

    return this.http.get<ClientDTO[]>(this.API_URL).pipe(
      catchError(this.handleError)
    );
  }

  

  // Get client by ID
  getClientById(id: number): Observable<ClientDTO> {
    return this.http.get<ClientDTO>(`${this.API_URL}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Create new client
  createClient(client: any): Observable<ClientDTO> {
    console.log("CLIENTService",client)
    return this.http.post<ClientDTO>(this.API_URL+'/create', client).pipe(
      catchError(this.handleError)
    );
  }

  // Update client
  updateClient(id: number, client: UpdateClientDTO): Observable<ClientDTO> {
    return this.http.put<ClientDTO>(`${this.API_URL}/${id}`, client).pipe(
      catchError(this.handleError)
    );
  }

  // Delete client
  deleteClient(id: any): Observable<void> {
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
