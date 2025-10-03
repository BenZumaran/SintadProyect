import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { HttpClient } from '@angular/common/http';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserLogin } from '../entity/client/user-login';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  private clientService = inject(ClientService);
  httpClient = inject(HttpClient);

  @Output() loginEvent = new EventEmitter<boolean>();
  token: string = '';
  loginForm!: FormGroup;

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(15),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
      ]),
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.getLoginToken(this.loginForm.value);
      this.loginEvent.emit(true);
    }
  }
  getLoginToken(user: UserLogin) {
    this.clientService
      .postLogin(user)
      .subscribe((data) => localStorage.setItem('token', data));
  }
}
