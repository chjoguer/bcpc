import { Component } from '@angular/core';
import { CuentasApiService } from './services/cuentas-api.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { accountDTO } from './dto/accountDTO';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';
import { TableComponent } from '../shared/table/table.component';
import { ModalComponent } from '../shared/modal/modal.component';

@Component({
  selector: 'app-cuentas',
  imports: [CommonModule, ReactiveFormsModule, TableComponent, ModalComponent], // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './cuentas.component.html',
  styleUrl: './cuentas.component.css',
  providers: [CuentasApiService],
})
export class CuentasComponent {
  accountForm!: FormGroup;
  accounts: any[] = [];
  searchTerm$ = new Subject<string>();
  loading = false;
  error = '';
  successMessage = '';
  submitted = false;
  errorMessage = '';

  tableColumns = [
    { key: 'numberAccount', title: '# Cuenta' },
    { key: 'typeAccount', title: 'Tipo Cuenta' },
    { key: 'initialAmount', title: 'Saldo Inicial' },
    { key: 'status', title: 'Status' },
  ];

  tableData: any[] = [];

  customFields = [
    { id: 'identification', label: 'Identificación', type: 'text', value: '' },
    { id: 'numberAccount', label: '# Cuenta', type: 'text', value: '' },
    { id: 'initialAmount', label: '$ Saldo Inicial', type: 'text', value: '' },
    { id: 'typeAccount', label: 'Tipo Cuenta', type: 'text', value: '' },
    { id: 'status', label: 'Estado', type: 'text', value: null },
  ];

  mappedCustomFields(customFields: any[], selectedRow: any): void {
    customFields[0]['value'] = selectedRow.identification;
    customFields[1]['value'] = selectedRow.numberAccount;
    customFields[2]['value'] = selectedRow.initialAmount;
    customFields[3]['value'] = selectedRow.typeAccount;
    customFields[4]['value'] = selectedRow.status;

    this.customFields = customFields;
    console.log('Selected mappedCustomFields:', customFields);
  }

  constructor(
    public accountService: CuentasApiService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadAccount();

    this.searchTerm$
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe((term) => {
        console.log('Search term:', term);
        if (term.length == 0) {
          this.loadAccount();
        }
        this.accounts = [];
        this.tableData = [];

        this.loadClientsByIdentification(term);
      });
  }

  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement) {
      const term = inputElement.value.trim();
      this.searchTerm$.next(term);
    }
  }

  loadClientsByIdentification(search?: string) {
    this.loading = true;
    this.error = '';

    this.accountService.getAccountById(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        if (response != null) {
          this.accounts.push(response);
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
      },
    });
  }

  loadAccount(search?: string): void {
    this.loading = true;
    this.error = '';

    this.accountService.getAccounts(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        if (response != null) {
          this.accounts.push(response);
        }
        if (response?.length != 0) {
          this.accounts = response;
          this.tableData = response;
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
      },
    });
  }

  isModalOpen = false;
  selectedRow: any = null;

  openModal(row: any) {
    console.log('Opening modal with dataxxxx:', row);
    this.selectedRow = row;
    this.isModalOpen = true;
    this.mappedCustomFields(this.customFields, this.selectedRow);
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedRow = null;
  }

  deleteRow(id: number) {
    console.log('Deleting row with id:', id);
    this.tableData = this.tableData.filter((row) => row.id !== id);
  }

  onModalSubmit(fields: any) {
    console.log('Submitted Fields:', fields);
    this.isModalOpen = false;
  }
}
