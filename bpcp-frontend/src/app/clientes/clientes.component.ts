import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { ClientDTO } from './dto/clientDTO';
import { ClientApiServiceService } from './services/client-api-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TableComponent } from '../shared/table/table.component';
import { ModalComponent } from '../shared/modal/modal.component';

@Component({
  selector: 'app-clientes',
  imports: [CommonModule,ReactiveFormsModule,TableComponent,ModalComponent],  // Import CommonModule to use *ngIf, *ngFor, etc.
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

    tableColumns = [
      { key: 'name', title: 'Nombre' },
      { key: 'identification', title: 'Identificación' },
      { key: 'age', title: 'Edad' },
      { key: 'phone', title: 'Telefono' },

    ];

    tableData: any[] = [];

    customFields = [
      { id: 'name', label: 'Nombre', type: 'text', value: '' },
      { id: 'identification', label: 'Identificación', type: 'text', value: '' },
      { id: 'age', label: 'Edad', type: 'text', value: '' },
      { id: 'phone', label: 'Teléfono', type: 'text', value: '' },
      { id: 'gender', label: 'Genero', type: 'text', value: '' },
      { id: 'password', label: 'Contrasenia', type: 'text', value: '' },
      { id: 'status', label: 'Estado', type: 'text', value: null },
    ];


    mappedCustomFields(customFields: any[],selectedRow:any): void {
      customFields[0]['value'] = selectedRow.name;
      customFields[1]['value'] = selectedRow.identification;
      customFields[2]['value'] = selectedRow.age;
      customFields[3]['value'] = selectedRow.phone;
      customFields[4]['value'] = selectedRow.gender;
      customFields[5]['value'] = selectedRow.password;
      customFields[6]['value'] = selectedRow.status;

  
      this.customFields = customFields;
      console.log('Selected mappedCustomFields:', customFields);
  
    }

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
        this.clientes = [];
        this.loadClients();
      }if(term.length > 0){
        this.clientes = [];
        this.loadClientsByIdentification(term);
      }
     
    });

  }
  
  loadClientsByIdentification(search?: string){
    console.log("ENtrando",search)
    this.loading = true;
    this.error = '';
    this.clientes = [];
    this.tableData = [];

    this.clientService.getClients(search).subscribe({
      next: (response) => {
        console.log('Response received identifica:', response); 
        if(response != null){
          console.log('Response is not null:', response); // Debug log
          this.error = 'No se encontraron resultados';
          this.clientes = [];
          // this.tableData = [];
          this.clientes.push(response);
          console.log("CLIENTES",this.clientes)
          //this.tableData = response;
          this.tableData.push(response);

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

  loadClients(): void {
    this.loading = true;
    this.error = '';
    console.log("ENtrando")
    console.log(this.tableData)
    console.log(this.clientes)
    this.clientes = [];
    this.tableData = [];

    this.clientService.getClients().subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        
        this.clientes = response;
        console.log("CLIENTES",this.clientes)

        this.tableData = response;
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


  isModalOpenAlert=false;
  openModalAlert(row: any){
    console.log('Opening deleting with dataxxxx:', row);
    this.selectedRow = row;
    this.isModalOpenAlert = true;
    console.log('Opening modal with dataxxxx222:', this.selectedRow,this.customFields); 

    this.mappedCustomFields(this.customFields,this.selectedRow);

  }
  closeModalAlert() {
    this.isModalOpenAlert = false;
    this.selectedRow = null;
  }



  isModalOpen = false;
  selectedRow: any = null;

 

  openModal(row: any) {
    this.selectedRow = row;
    this.isModalOpen = true;

    this.mappedCustomFields(this.customFields,this.selectedRow);
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedRow = null;
  }

  deleteRow(row: any) {
    console.log('Deleting row:', row);
    console.log('Opening deleting with dataxxxx333:', row); 
    this.isModalOpenAlert = true;
    this.selectedRow = row;

    console.log('Opening modal with dataxxxx222:', this.selectedRow,this.customFields); 

    this.mappedCustomFields(this.customFields,this.selectedRow);
  }

  onModalSubmitAlert(fields: any) {
    console.log('Submitted Alerts:', fields);
    this.clientService.deleteClient(fields['identification']).subscribe({
      next: () => {
        this.loadClients();
      },
      error: (err) => {
        console.error('Error deleting client:', err);
        alert('Ocurrió un error al eliminar el cliente');
      }
    });
  }


  onModalSubmit(fields: any) {
    console.log('Submitted Fields:', fields);
    this.clientService.updateClient(fields.identification,fields).subscribe({
      next: (response: any) => {
        this.successMessage = 'Client updated successfully!';
        console.log('Client updated:', response);
        this.loadClients();
      },
      error: (error: Error) => {
        this.error = error.message;
        console.error('Error creating client:', error);
        this.loading = false;
      }
    });
    this.isModalOpen = false;
  }

  
}
