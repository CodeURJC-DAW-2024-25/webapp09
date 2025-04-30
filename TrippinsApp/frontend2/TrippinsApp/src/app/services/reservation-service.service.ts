import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { ReservationDTO } from '../models/DTOS/reservation-dto';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  constructor(private http:HttpClient) { }

  createReservation(data:any ):Observable<ReservationDTO>{


    return this.http.post<ReservationDTO>(`${environment.baseUrlApi}/reservations`, data)
  }

  
}
