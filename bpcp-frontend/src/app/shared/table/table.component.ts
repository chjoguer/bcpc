import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-table',
  imports: [CommonModule,FormsModule],  // Import CommonModule to use *ngIf, *ngFor, etc.
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {
  @Input() columns: { key: string, title: string }[] = [];
  @Input() data: any[] = [];

  @Output() deleteRow = new EventEmitter<any>();
  @Output() openModal = new EventEmitter<any>();
  @Output() openModalAlert = new EventEmitter<any>();

  onDelete(row: any) {
    console.log('Delete row:', row);
    this.deleteRow.emit(row);
    this.openModalAlert.emit(row);

  }

  onEdit(row: any) {
    this.openModal.emit(row);
    console.log('Edit row:', this.openModal);
    console.log('Edit row:', row);
  }

  
}
