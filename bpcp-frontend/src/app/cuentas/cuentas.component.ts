import { Component } from '@angular/core';
import { CuentasApiService } from './services/cuentas-api.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { accountDTO } from './dto/accountDTO';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';

@Component({
  selector: 'app-cuentas',
  imports: [CommonModule,ReactiveFormsModule],  // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './cuentas.component.html',
  styleUrl: './cuentas.component.css',
  providers: [CuentasApiService] 

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

  constructor(public accountService: CuentasApiService, private fb: FormBuilder) {
  

  };
  


  ngOnInit(): void {
    this.loadAccount();

     this.searchTerm$.pipe(
          debounceTime(300),
          distinctUntilChanged()
          
        ).subscribe(term => {
          console.log('Search term:', term);
          if(term.length == 0){
            this.loadAccount();
          }
          this.accounts = [];
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

  loadClientsByIdentification(search?: string){
    this.loading = true;
    this.error = '';

    this.accountService.getAccountById(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        if(response != null){
          this.accounts.push(response);
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

 
  loadAccount(search?: string): void {
    this.loading = true;
    this.error = '';

    this.accountService.getAccounts(search).subscribe({
      next: (response) => {
        console.log('Response received:', response); // Debug log
        if(response != null){
          this.accounts.push(response);
        }
        if(response?.length != 0){
          this.accounts = response;

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
}
