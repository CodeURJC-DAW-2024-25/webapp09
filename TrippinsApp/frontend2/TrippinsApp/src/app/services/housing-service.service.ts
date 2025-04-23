import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HousingDTO } from '../models/DTOS/housing-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HousingServiceService {

  constructor(private http: HttpClient) { }


  getRooms(): Observable<HousingDTO[]>{
  return this.http.get<HousingDTO[]>(`${environment.baseUrlApi}/houses`)
  }

}
