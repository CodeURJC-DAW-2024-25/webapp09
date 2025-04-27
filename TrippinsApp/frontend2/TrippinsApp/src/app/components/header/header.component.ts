import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserRole } from '../../models/DTOS/user-dto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  standalone:false
})
export class HeaderComponent {
  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  get username(): string | null {
    // Extract username from JWT if needed
    // This is a simple implementation - you might need to adjust based on your JWT structure
    const token = this.authService.getJwt();
    if (!token) return null;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.sub || payload.username || null;
    } catch (e) {
      return null;
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAdmin(): boolean {
    return this.authService.hasRole('ROLE_ADMIN');
  }

  isUser(): boolean {
    return this.authService.hasRole('ROLE_USER') || this.isAdmin();
  }
}
