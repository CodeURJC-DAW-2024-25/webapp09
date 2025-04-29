import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HousingDTO, PagedResponse } from '../models/DTOS/housing-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HousingServiceService {

  constructor(private http: HttpClient) { }


  getRooms(page: number, size: number): Observable<PagedResponse<HousingDTO>>{
  return this.http.get<PagedResponse<HousingDTO>>(`${environment.baseUrlApi}/rooms/extra?page=${page}&size=6`)
  }
  searchHouses(tags:string, stars: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.baseUrlApi}/query?tags=${tags}&stars=${stars}`);
  }

}
