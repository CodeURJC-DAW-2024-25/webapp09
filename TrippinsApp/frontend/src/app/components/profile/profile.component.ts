import { Component } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { UserDTO } from '../../models/DTOS/user-dto';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ReservationDTO } from '../../models/DTOS/reservation-dto';
import { UserServiceService } from '../../services/user-service.service';
import { ReservationServiceService } from '../../services/reservation-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  standalone:false
})
export class ProfileComponent {
  profileForm: FormGroup;
  isEditing = false;
  user: UserDTO | null = null;
  reservations: ReservationDTO[] = [];
  isLoading = true;

  constructor(
    private authService: AuthService,
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    private userService: UserServiceService,
    private reservationService: ReservationServiceService
  ) {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dni: ['', Validators.required],
      number: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {

    this.loadReservations();
  }



  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe({
      next: (reservations) => {
        if (this.user) {
          this.reservations = reservations.filter(r => 
            (r as any).clientDni === this.user?.dni
          );
        }
      },
      error: (err) => {
        console.error('Error loading reservations:', err);
      }
    });
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
    if (this.isEditing) {
      this.profileForm.enable();
      this.profileForm.get('dni')?.disable(); // DNI is the main id so it doesnt let you edit
    } else {
      this.profileForm.disable();
    }
  }

  onSubmit(): void {
    if (this.profileForm.invalid || !this.user) return;

    const updatedUser = {
      ...this.profileForm.value,
      dni: this.user.dni, // Ensure DNI doesn't get changed
      roles: this.user.roles
    };

    this.userService.updateAccount(updatedUser,this.user.dni)
      .subscribe({
        next: () => {
          this.isEditing = false;
          this.profileForm.disable();
          // Update local user data
          this.user = { ...this.user!, ...updatedUser };
          // Update username in auth service if name changed
          if (this.authService.getUsername !== updatedUser.email) {
            this.authService.updateUsername(updatedUser.name);
          }
        },
        error: (err) => {
          console.error('Error updating user:', err);
        }
      });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['new/login']);
  }

  getReservationStatus(valorated: boolean): string {
    return valorated ? 'Aceptado' : 'Pendiente';
  }
}
