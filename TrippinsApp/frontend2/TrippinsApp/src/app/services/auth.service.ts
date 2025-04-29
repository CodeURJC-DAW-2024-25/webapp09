// auth.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { AuthenticationRequest, AuthenticationResponse, UserDTO } from '../models/DTOS/user-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly JWT_KEY = 'auth_jwt';
  private readonly ROLES_KEY = 'user_roles';
  private username: string | null = null;

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${environment.baseUrlApi}/login`, credentials).pipe(
      tap(response => {
        this.storeJwt(response.jwt);
        this.storeRoles(response.roles);
        this.username = credentials.email;
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
    var currentRoles: string [] = this.getRoles();
    for (let index = 0; index < roles.length; index++) {
      if (currentRoles.includes(roles[index])){
        return true;
      }
      
    }
    return false;
  }

  isLoggedIn(): boolean {
    return !!this.getJwt();
  }

  logout(): void {
    localStorage.removeItem(this.JWT_KEY);
    localStorage.removeItem(this.ROLES_KEY);
  }

  getUsername(): string | null {
    if (this.username) return this.username;
    
    // Fallback to JWT parsing if username not set
    const token = this.getJwt();
    if (!token) return null;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.sub || payload.username || null;
    } catch (e) {
      return null;
    }
  }
  
  updateUsername(newUsername: string): void {
    this.username = newUsername;
  }


  getUserDni(): Observable<string | null>{
    const usernameemail = this.getUsername();
    return this.http.get<UserDTO[]>(`${environment.baseUrlApi}/users`).pipe(
      map(users => {
        const user = users.find(u => u.email === usernameemail);
        return user ? user.dni : null;
      }),
      catchError(err => {
        console.error('Error loading user data:', err);
        return of(null);
      })
    );

  }
  getUserName(): Observable<string | null>{
    const usernameemail = this.getUsername();
    return this.http.get<UserDTO[]>(`${environment.baseUrlApi}/users`).pipe(
      map(users => {
        const user = users.find(u => u.email === usernameemail);
        return user ? user.name : null;
      }),
      catchError(err => {
        console.error('Error loading user data:', err);
        return of(null);
      })
    );

  }
}