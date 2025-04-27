import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReservationDTO } from '../../../models/DTOS/reservation-dto';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-reservation-panel',
  templateUrl: './reservation-panel.component.html',
  styleUrl: './reservation-panel.component.css',
  standalone: false
})
export class ReservationPanelComponent {

  reservations: ReservationDTO[] = [];
  currentPage = 0;
  isLoading = false;
  size = 3;

  constructor(private http: HttpClient) {
    this.loadReservations();
  }

  loadReservations() {
    if (this.isLoading) return;

    this.isLoading = true;
    this.http.get<ReservationDTO[]>(`${environment.baseUrlApi}/admin/reservations?page=${this.currentPage}&size=${this.size}`)
      .subscribe({
        next: (data) => {
          this.reservations = [...this.reservations, ...data];
          this.currentPage++;
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error loading reservations:', err);
          this.isLoading = false;
        }
      });
  }

  handleDecision(reservationId: number, decision: 'accept' | 'reject') {

    if (decision == 'accept') {
      this.http.put(`${environment.baseUrlApi}/admin/reservations/decision/${reservationId}`, {})
        .subscribe({
          next: () => {
            this.reservations = this.reservations.filter(r => r.id !== reservationId);
          },
          error: (err) => {
            console.error('Error processing decision:', err);
          }
        });
    }
    else {
      this.http.delete(`${environment.baseUrlApi}/admin/reservations/decision/${reservationId}`, {})
        .subscribe({
          next: () => {
            this.reservations = this.reservations.filter(r => r.id !== reservationId);
          },
          error: (err) => {
            console.error('Error processing decision:', err);
          }
        });
    }
  }
}
