// auth.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { AuthenticationRequest, AuthenticationResponse } from '../models/DTOS/user-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly JWT_KEY = 'auth_jwt';
  private readonly ROLES_KEY = 'user_roles';

  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${environment.baseUrlApi}/login`, credentials).pipe(
      tap(response => {
        this.storeJwt(response.jwt);
        this.storeRoles(response.roles);
      })
    );
  }

  private storeJwt(jwt: string): void {
    localStorage.setItem(this.JWT_KEY, jwt);
  }

  private storeRoles(roles: string[]): void {
    localStorage.setItem(this.ROLES_KEY, JSON.stringify(roles));
  }

  getJwt(): string | null {
    return localStorage.getItem(this.JWT_KEY);
  }

  getRoles(): string[] {
    const rolesJson = localStorage.getItem(this.ROLES_KEY);
    return rolesJson ? JSON.parse(rolesJson) : [];
  }

  hasRole(role: string): boolean {
    return this.getRoles().includes(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return this.getRoles().some(userRole => roles.includes(userRole));
  }

  isLoggedIn(): boolean {
    return !!this.getJwt();
  }

  logout(): void {
    localStorage.removeItem(this.JWT_KEY);
    localStorage.removeItem(this.ROLES_KEY);
  }
}