import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { ClientEntity } from '../entity/client/client-entity';
import { catchError, map, of } from 'rxjs';
import { OptionsButtonComponent } from '../options-button/options-button.component';
import { ClientEditModalComponent } from '../client-edit-modal/client-edit-modal.component';

@Component({
  selector: 'app-clients-list',
  standalone: true,
  imports: [OptionsButtonComponent, ClientEditModalComponent],
  templateUrl: './clients-list.component.html',
  styleUrl: './clients-list.component.css',
})
export class ClientsListComponent implements OnInit {
  private clientService = inject(ClientService);
  httpClient = inject(HttpClient);

  clients: ClientEntity[] = [];
  mode: string = 'new';
  showEditModal: boolean = false;
  currentPage: number = 0;
  totalPages: number = 0;

  ngOnInit(): void {
    this.loadClients();
    this.loadCount();
  }

  toggleShowEditModal() {
    this.showEditModal = true;
  }

  changeState(event: Event) {
    const currentValues = (event.target as HTMLInputElement).value.split('|');
    this.updateClient(
      parseInt(currentValues[0]),
      currentValues[1] === 'active'
    );
    this.loadClients(this.currentPage);
  }

  updateClient(id: number, estado: boolean) {
    return this.clientService
      .patchUpdateClientEstado(id, estado)
      .pipe(
        map((data) => true),
        catchError((error) => {
          console.error(error);
          return of(false);
        })
      )
      .subscribe((data) => {
        if (data) {
          alert('Cliente actualizado correctamente.');
        } else alert('Error al actualizar el cliente');
      });
  }

  loadClients(page: number = 0) {
    this.currentPage = page;
    this.clientService
      .getAllClients(page)
      .pipe(
        map((data) => {
          data.fecha_registro = new Date(data.fecha_registro).toDateString();
          return data;
        })
      )
      .subscribe((data: any) => {
        console.log(data);
        this.clients = data;
      });
  }

  loadCount() {
    this.clientService
      .getClientCount()
      .pipe(map((data) => Math.ceil(data / 5)))
      .subscribe((data: any) => {
        this.totalPages = data;
      });
  }
}
