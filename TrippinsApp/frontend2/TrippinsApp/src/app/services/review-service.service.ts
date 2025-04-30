import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PagedResponse } from '../models/DTOS/housing-dto';
import { ReviewDTO } from '../models/DTOS/review-dto';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ReviewServiceService {

  constructor(private http: HttpClient) { }

  getPaginatedComments(code:number, page: number): Observable<PagedResponse<ReviewDTO>>{
    return this.http.get<PagedResponse<ReviewDTO>>(`${environment.baseUrlApi}/rooms/${code}/comments/extra?page=${page}&size=6`)
  }
  
  createReview(commentData: any):Observable<ReviewDTO>{

    return this.http.post<ReviewDTO>(`${environment.baseUrlApi}/reviews`, commentData)
  }
}
