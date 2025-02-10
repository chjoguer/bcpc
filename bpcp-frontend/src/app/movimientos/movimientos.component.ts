import { Component, ViewChild,ElementRef } from '@angular/core';
import { MovimientosApiService } from './services/movimientos-api.service';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap'; // If using ng-bootstrap
import { movementDTO } from './dto/movementDTO';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { CommonModule } from '@angular/common';
import { TableComponent } from '../shared/table/table.component';
import { ModalComponent } from '../shared/modal/modal.component';

@Component({
  selector: 'app-movimientos',
  imports: [CommonModule, ReactiveFormsModule,TableComponent,NgbModule,ModalComponent], // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './movimientos.component.html',
  styleUrl: './movimientos.component.css',
  providers: [MovimientosApiService],
})
export class MovimientosComponent {
  @ViewChild('createMovementModal') createMovementModal!: ElementRef; // Reference to modal element

  movementForm!: FormGroup;
  movements: movementDTO[] = [];
  searchTerm$ = new Subject<string>();
  loading = false;
  error = '';
  successMessage = '';
  submitted = false;
  errorMessage = '';

  
  tableColumns = [
    { key: 'numberAccount', title: '# Cuenta' },
    { key: 'typeMovement', title: 'Tipo Cuenta' },
    { key: 'initialAmount', title: 'Saldo Inicial	' },
    { key: 'status', title: 'Status' },
    { key: 'movementAmount', title: 'Movimiento' },
  ];

  customFields = [
    { id: 'id', label: 'Moviemiento ID', type: 'number', value: null },
    { id: 'numberAccount', label: '# Cuenta', type: 'text', value: '' },
    { 
      id: 'typeMovement', label: 'Tipo de Movimiento', type: 'select', value: '', 
      options: [
        { value: 'DEPOSIT', label: 'Depósito' },
        { value: 'WITHDRAWAL', label: 'Retiro' }
      ]
    },
    { id: 'movementAmount', label: 'Cantidad', type: 'number', value: null },
    { id: 'status', label: 'Status', type: 'number', value: null },
    { id: 'initialAmount', label: 'Cantidad Inicial', type: 'number', value: null },

    
  ];

  mappedCustomFields(customFields: any[],selectedRow:any): void {
    customFields[0]['value'] = selectedRow.id;
    customFields[1]['value'] = selectedRow.numberAccount;
    customFields[2]['value'] = selectedRow.typeMovement;
    customFields[3]['value'] = selectedRow.movementAmount;
    customFields[4]['value'] = selectedRow.status;
    customFields[5]['value'] = selectedRow.initialAmount;

    this.customFields = customFields;
    console.log('Selected mappedCustomFields......:', customFields);

  }




  tableData: movementDTO[] = [];


  constructor(
    public movementService: MovimientosApiService,
    private fb: FormBuilder,
    private modalService: NgbModal
  ) {
    this.movementForm = this.fb.group({
      // Adjust the fields to match your CreateClientDTO structure
      numberAccount: ['', Validators.required],
      typeMovement: ['', Validators.required],
      movementAmount: [
        '',
        [Validators.required, Validators.pattern('\\d+(\\.\\d{1,2})?')],
      ],

      // Add other fields as needed
    });
  }

  ngOnInit(): void {
    this.loadMovements();

    this.searchTerm$
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe((term) => {
        console.log('Search term:', term);
        if (term.length == 0) {
          this.loadMovements();
        }
        this.movements = [];
        this.loadMovements(term);
      });
  }

  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement) {
      const term = inputElement.value.trim();
      this.searchTerm$.next(term);
    }
  }

  onSubmit(): void {
    this.closeModal();
    this.submitted = true;
    this.errorMessage = '';
    this.successMessage = '';

    // Check if the form is valid
    console.log('BEFORE', this.movementForm);

    if (this.movementForm.invalid) {
      return;
    }
    const newMovement: movementDTO = this.movementForm.value;
    // const newMovement2: movementDTO={
    //   id: 2,
    //   status: "string",
    //   numberAccount: "752825",
    //   typeMovement: "string",
    //   initialAmount: "Double",
    //   movementAmount: "Double",

    // }
    const newMovement2: any = {
      numberAccount: this.movementForm.value['numberAccount'],
      movementAt: 'Current',
      typeMovement:
        this.movementForm.value['typeMovement'] == 'WITHDRAWAL'
          ? 'Retiro'
          : 'Deposito',
      movementAmount:
        this.movementForm.value['typeMovement'] == 'WITHDRAWAL'
          ? -this.movementForm.value['movementAmount']
          : this.movementForm.value['movementAmount'],
      status: 1,
    };
    console.log('AFTER', newMovement2);
    console.log('AFTER', this.movementForm.value);

    this.movementService.createMovement(newMovement2).subscribe({
      next: (response: any) => {
        // Handle the successful response here
        this.successMessage = 'Client created successfully!';
        console.log('Client created:', response);
        this.movementForm.reset();
        this.submitted = false;
        this.loading = false;
        this.loadMovements();
      },
      error: (error: Error) => {
        // Handle errors here
        this.error = error.message;
        console.error('Error creating client:', error);
        this.loading = false;
      },
    });
    console.log('AFTER', this.movementForm);
    this.modalService.dismissAll(); // Close modal (using ng-bootstrap modal service)

  }

  loadMovements(search?: string): void {
    this.loading = true;
    this.error = '';

    this.movementService.getMovements(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        this.movements = response;
        this.tableData = response;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching movements:', error);
        this.error = 'Error loading movements. Please try again later.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      },
    });
  }


  // closeModal() {
  //   const modalElement = document.getElementById('createMovementModal');
  //   console.log('modalElement', modalElement);
    
  //   if (modalElement) {
  //     // Remove show classes
  //     modalElement.classList.remove('show');
  //     modalElement.classList.remove('in');
      
  //     // Set display to none
  //     modalElement.style.display = 'none';
      
  //     // Remove modal backdrop
  //     const backdrop = document.querySelector('.modal-backdrop');
  //     if (backdrop) {
  //       backdrop.remove();
  //     }
      
  //     // Remove body classes added by Bootstrap modal
  //     document.body.classList.remove('modal-open');
  //   }
  // }

  isModalOpen = false;
  selectedRow: any = null;

 

  openModal(row: any) {
    console.log('Opening modal with dataxxxx:', row); 
    this.selectedRow = row;
    this.isModalOpen = true;
    this.mappedCustomFields(this.customFields,this.selectedRow);
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedRow = null;
  }

  deleteRow(row: any) {
    console.log('Deleting ROW dataxxxx:', row); 
    this.isModalOpenAlert = true;
    this.selectedRow = row;

    console.log('Opening modal with movement:', this.selectedRow,this.customFields); 

    this.mappedCustomFields(this.customFields,this.selectedRow);
    // this.tableData = this.tableData.filter(row => row.id !== id);
  }

  onModalSubmit(fields: any) {
    console.log('Submitted Fields:', fields['id']);
    console.log('Submitted Fields:', fields);

    this.movementService.updateMovement(fields['id'],fields).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        this.loadMovements();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error fetching movements:', error);
        this.error = 'Error loading movements. Please try again later.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      },
    });
    this.isModalOpen = false;
  }

  isModalOpenAlert=false;


  onModalSubmitAlert(fields: any) {
    console.log('Submitted Alerts:', fields);
    this.movementService.deleteMovement(fields['id']).subscribe({
      next: () => {
        this.loadMovements();
      },
      error: (err) => {
        console.error('Error deleting movements:', err);
        alert('Ocurrió un error al eliminar el movimiento');
      }
    });
  }

  closeModalAlert() {
    this.isModalOpenAlert = false;
    this.selectedRow = null;
  }

  
}
