import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ClientEntity } from '../../entity/client/client-entity';
import { ClienRequestEntity } from '../../entity/client/client-request-entity';
import { map } from 'rxjs';
import { UserLogin } from '../../entity/client/user-login';

const BASE_URL = 'http://localhost:8080/api/v1/clientes';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private http = inject(HttpClient);
  constructor() {}
  getAllClients(page: number) {
    const params = new HttpParams({
      fromObject: {
        limit: 5,
        offset: page * 5,
      },
    });
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<ClientEntity>(BASE_URL, {
      params: params,
      headers,
    });
  }
  getClientById(id: number) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<ClientEntity>(`${BASE_URL}/${id}`, { headers });
  }
  getClientCount() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<number>(`${BASE_URL}/count`, { headers });
  }
  postNewClient(client: ClienRequestEntity) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<ClientEntity>(BASE_URL, client, { headers });
  }
  putUpdateClient(id: number, client: ClienRequestEntity) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<ClientEntity>(`${BASE_URL}/${id}`, client, {
      headers,
    });
  }
  patchUpdateClientEstado(id: number, estado: boolean) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.patch<ClientEntity>(`${BASE_URL}/${id}/${estado}`, null, {
      headers,
    });
  }
  deleteClient(id: number) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<ClientEntity>(`${BASE_URL}/${id}`, { headers });
  }
  postLogin(user: UserLogin) {
    return this.http.post(`${BASE_URL}/login`, user, { responseType: 'text' });
  }
}
