import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { ClientDTO } from './dto/clientDTO';
import { ClientApiServiceService } from './services/client-api-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-clientes',
  imports: [CommonModule,ReactiveFormsModule],  // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css'],
  providers: [ClientApiServiceService]  // Add this

})
export class ClientesComponent implements OnInit {
  // clientes = [
  //   { nombre: 'Juan', apellido: 'Pérez', email: 'juan.perez@example.com' },
  //   { nombre: 'Ana', apellido: 'Gómez', email: 'ana.gomez@example.com' },
  //   { nombre: 'Luis', apellido: 'Martínez', email: 'luis.martinez@example.com' }
  // ];
  clientForm!: FormGroup;
  clientes: any[] = [];
  searchTerm$ = new Subject<string>();
  loading = false;
  error = '';
  successMessage = '';
  submitted = false;
  errorMessage = '';

  constructor(public clientService: ClientApiServiceService, private fb: FormBuilder) {

    this.clientForm = this.fb.group({
      name: ['', Validators.required],
      identification: ['',Validators.required],
      phone: ['', Validators.required],
      gender: ['', Validators.required],
      age: ['', Validators.required],
      password: ['', Validators.required],
      status: ['', Validators.required],
    });


    
  }

  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement) {
      const term = inputElement.value.trim();
      this.searchTerm$.next(term);
    }
  }


  ngOnInit(): void {
    this.loadClients();

    this.searchTerm$.pipe(
      debounceTime(300),
      distinctUntilChanged()
      
    ).subscribe(term => {
      console.log('Search term:', term);
      if(term.length == 0){
        this.loadClients();
      }
      this.clientes = [];
      this.loadClientsByIdentification(term);
    });

  }
  
  loadClientsByIdentification(search?: string){
    this.loading = true;
    this.error = '';
    this.clientService.getClients(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); 
        if(response != null){
          this.error = 'No se encontraron resultados';
          this.clientes.push(response);

        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching clients:', error);
        this.error = 'Error loading clients. Please try again later.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  loadClients(search?: string): void {
    this.loading = true;
    this.error = '';

    this.clientService.getClients(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        
        this.clientes = response;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching clients:', error);
        this.error = 'Error loading clients. Please try again later.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  deleteClient(id: string): void {
    if (confirm('¿Estás seguro de que quieres eliminar este cliente?')) {
      this.clientService.deleteClient(id).subscribe({
        next: () => {
          this.loadClients();
        },
        error: (err) => {
          console.error('Error deleting client:', err);
          alert('Ocurrió un error al eliminar el cliente');
        }
      });
    }
  }


  
  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';


    if (this.clientForm.invalid) {
      return;
    }
    this.loading = true;
    const newClient: ClientDTO = this.clientForm.value;
   
    this.clientService.createClient(newClient).subscribe({
      next: (response: any) => {
        
        this.successMessage = 'Client created successfully!';
        console.log('Client created:', response);
        this.clientForm.reset();
        this.submitted = false;
        this.loading = false;
        this.loadClients();
      },
      error: (error: Error) => {
        this.error = error.message;
        console.error('Error creating client:', error);
        this.loading = false;
      }
    });
  }
}
