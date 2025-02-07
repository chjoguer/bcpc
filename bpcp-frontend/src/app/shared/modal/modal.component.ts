import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
  imports: [CommonModule,FormsModule],  // Import CommonModule to use *ngIf, *ngFor, etc.
  
})
export class ModalComponent {
  @Input() title: string = 'Modal Title';
  @Input() isOpen: boolean = false;
  @Input() data: any = null;

  @Output() closeModal = new EventEmitter<void>();
  @Output() submitForm = new EventEmitter<any>();

  @Input() fields: any[] = []; // Array to hold custom fields

  close() {
    this.closeModal.emit();
  }

  mapperFields(fields:any): any {
    return this.fields.reduce((acc, field) => {
      acc[field.id] = field.value;
      return acc;
    }, {});
  }

  submit() {
    // Emit the form data when submitted
    this.submitForm.emit(this.mapperFields(this.fields));
    this.close();
  }

}
