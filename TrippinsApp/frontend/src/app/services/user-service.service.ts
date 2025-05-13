import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from '../models/DTOS/user-dto';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient) { }


  createAccount(data: any):Observable<UserDTO>{

    return this.http.post<UserDTO>(`${environment.baseUrlApi}/users`,data)
  }

  updateAccount(data: any, dni: string): Observable<UserDTO>{
    return this.http.put<UserDTO>(`${environment.baseUrlApi}/users/${dni}`, data)
  }
}
