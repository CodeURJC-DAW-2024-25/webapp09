import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReservationDTO } from '../../../models/DTOS/reservation-dto';

@Component({
  selector: 'app-reservation-panel',
  templateUrl: './reservation-panel.component.html',
  styleUrl: './reservation-panel.component.css',
  standalone: false
})
export class ReservationPanelComponent {

  @Input() reservations: ReservationDTO[];
  @Output() approve = new EventEmitter<number>();

}
