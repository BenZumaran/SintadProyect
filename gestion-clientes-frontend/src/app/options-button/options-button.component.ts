import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
import { ClientEntity } from '../entity/client/client-entity';
import { ClientEditModalComponent } from '../client-edit-modal/client-edit-modal.component';

@Component({
  selector: 'app-options-button',
  standalone: true,
  imports: [ClientEditModalComponent],
  templateUrl: './options-button.component.html',
  styleUrl: './options-button.component.css',
})
export class OptionsButtonComponent {
  private clientService = inject(ClientService);
  httpClient = inject(HttpClient);

  @Input() clientId: number = 0;
  @Output() refreshTableEvent = new EventEmitter<void>();
  showOptions: boolean = false;
  cliente: ClientEntity | undefined;
  showEditModal: boolean = false;
  mode: string = 'edit';

  toggleShowOptions() {
    this.showOptions = !this.showOptions;
  }

  editCliente() {
    this.loadCliente();
    this.showEditModal = true;
    this.showOptions = false;
    this.cliente = undefined;
  }

  deleteCliente() {
    if (this.clientId === undefined) return;
    this.clientService.deleteClient(this.clientId).subscribe((data) => {
      alert('Cliente eliminado');
      this.refreshTableEvent.emit();
      console.log(data);
    });
    this.showOptions = false;
  }

  loadCliente() {
    console.log(this.clientId);
    if (this.clientId === undefined) return;
    this.clientService
      .getClientById(this.clientId)
      .pipe(
        map((data) => {
          data.fecha_registro = new Date(data.fecha_registro).toDateString();
          return data;
        })
      )
      .subscribe((data: any) => {
        console.log(data);
        this.cliente = data;
      });
  }
}
