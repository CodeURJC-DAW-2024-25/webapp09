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

  getSpecificRoom(code: number): Observable<HousingDTO>{
    
    return this.http.get<HousingDTO>(`${environment.baseUrlApi}/houses/${code}`)
  }
  createRoom(data: any):Observable<HousingDTO>{

    return this.http.post<HousingDTO>(`${environment.baseUrlApi}/houses`, data)
  }


  uploadHousingImage(code: number, image: File){
    const formData = new FormData();
    formData.append('file', image);
    return this.http.put<HousingDTO>(`${environment.baseUrlApi}/houses/${code}/image`, formData)
  }
}
