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
export class HeaderComponent implements OnInit {
  loading = true;
  UserRole = UserRole; // Make enum available in template

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loading = false;
  }

  logout(): void {
    this.authService.logout();
  }

  get username(): string {
    return this.authService.currentUserValue?.name || '';
  }

  get isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  isAdmin(): boolean {
    return this.authService.hasRole(UserRole.ROLE_ADMIN);
  }

}
