import { Component } from '@angular/core';
import { UserMenuComponent } from '../user-menu/user-menu.component';
import { AsideMenuComponent } from '../aside-menu/aside-menu.component';
import { ClientsListComponent } from '../clients-list/clients-list.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [UserMenuComponent, AsideMenuComponent, ClientsListComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {}
