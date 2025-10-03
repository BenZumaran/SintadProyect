import {
  Component,
  inject,
  Input,
  OnInit,
  model,
  Output,
  EventEmitter,
} from '@angular/core';
import { ClientEntity } from '../entity/client/client-entity';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { catchError, map, Observable, of } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ClientService } from '../services/client/client.service';
import { HttpClient } from '@angular/common/http';
import { error } from 'console';

@Component({
  selector: 'app-client-edit-modal',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './client-edit-modal.component.html',
  styleUrl: './client-edit-modal.component.css',
})
export class ClientEditModalComponent implements OnInit {
  private clientService = inject(ClientService);
  httpClient = inject(HttpClient);
  @Input() cliente: ClientEntity | undefined;
  isEditModalOpen = model<boolean>(false);
  @Input() mode: string | undefined;
  @Output() refreshTableEvent = new EventEmitter<void>();

  clientForm!: FormGroup;

  ngOnInit(): void {
    this.clientForm = new FormGroup({
      nombre: new FormControl(
        this.mode === 'edit' ? this.cliente?.nombre : '',
        [Validators.required, Validators.minLength(2), Validators.maxLength(50)]
      ),
      apellido: new FormControl(
        this.mode === 'edit' ? this.cliente?.apellido : '',
        [Validators.required, Validators.minLength(2)]
      ),
      telefono: new FormControl(
        this.mode === 'edit' ? this.cliente?.telefono : '',
        [Validators.required, Validators.pattern(/^\d{9}$/)]
      ),
      correo: new FormControl(
        this.mode === 'edit' ? this.cliente?.correo : '',
        [Validators.required, Validators.email]
      ),
    });
  }

  toggleEditModal() {
    if (this.isEditModalOpen.subscribe((value) => value))
      this.isEditModalOpen.set(false);
    this.cliente = undefined;
    this.clientForm.reset();
  }

  onSubmit() {
    if (this.clientForm.valid) {
      if (this.mode === 'edit') {
        this.updateClient();
      } else {
        try {
          this.insertClient();
          alert('Cliente ingresado correctamente.');
          this.toggleEditModal();
        } catch (ex) {
          alert('Error al ingresar el cliente');
        }
      }
    } else {
      alert('Formulario invalido.');
    }
  }

  updateClient() {
    this.clientService
      .putUpdateClient(this.cliente!.id, this.clientForm.value)
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
          this.toggleEditModal();
          // this.refreshTableEvent.emit();
        } else alert('Error al actualizar el cliente');
      });
  }

  insertClient() {
    return this.clientService
      .postNewClient(this.clientForm.value)
      .pipe(
        map((data) => true),
        catchError((error) => {
          console.error(error);
          return of(false);
        })
      )
      .subscribe((data) => {
        if (data) {
          alert('Cliente ingresado correctamente.');
          this.toggleEditModal();
          this.refreshTableEvent.emit();
        } else alert('Error al ingresar el cliente');
      });
  }
}
