import { Component } from '@angular/core';
import { ReservationDTO } from '../../models/DTOS/reservation-dto';
import { HousingDTO } from '../../models/DTOS/housing-dto';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
  standalone:false
})
export class AdminComponent {
  activeTab: 'reservations' | 'houses' = 'reservations';

  switchTab(tab: 'reservations' | 'houses') {
    this.activeTab = tab;
  }
}
