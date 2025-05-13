import { Component } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ReviewDTO } from '../../models/DTOS/review-dto';
import { HousingDTO, PagedResponse } from '../../models/DTOS/housing-dto';
import Swal from 'sweetalert2';
import { ReservationServiceService } from '../../services/reservation-service.service';
import { ReviewServiceService } from '../../services/review-service.service';
import { HousingServiceService } from '../../services/housing-service.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrl: './room-details.component.css',
  standalone:false
})
export class RoomDetailsComponent {
  house: HousingDTO | null = null;
  comments: ReviewDTO[] = [];
  currentPage = 0;
  isLoading = false;
  clientDni: string | null = null;
  clientName: string | null = null;

  reservationForm: FormGroup;
  commentForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private fb: FormBuilder,
    public authService: AuthService,
    private reservationService: ReservationServiceService,
    private reviewService: ReviewServiceService,
    private housingService: HousingServiceService
  ) {
    this.reservationForm = this.fb.group({
      checkIn: ['', Validators.required],
      checkOut: ['', Validators.required]
    });

    this.commentForm = this.fb.group({
      comment: ['', [Validators.required, Validators.minLength(10)]],
      rating: [50, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  ngOnInit(): void {
    const code = this.route.snapshot.paramMap.get('id');

    if (code) {
      const code2 = Number(code);
      this.loadHouseDetails(code2);
      this.loadComments(code2, this.currentPage);
    }
    this.authService.getUserDni().subscribe(dni => {
      this.clientDni = dni;
    });
    this.authService.getUserName().subscribe(name => {
      this.clientName = name;
    });
  }

  loadHouseDetails(code: number): void {
    this.housingService.getSpecificRoom(code).subscribe({
      next: (house: any) => {
        this.house = {
          ...house,
          image: `${environment.baseUrlApi}/houses/${code}/image`
        };
      },
      error: (err: any) => {
        console.error('Error loading house details:', err);
      }
    });
  }

  loadComments(code: number, page : number): void {
    this.isLoading = true;
    this.reviewService.getPaginatedComments(code,page).subscribe({
      next: (comments: any) => {
        this.comments = [...this.comments, ...comments.content];
        this.currentPage = page;
        this.isLoading = false;
      },
      error: (err: any) => {
        console.error('Error loading comments:', err);
        this.isLoading = false;
      }
    });
  }

  onSubmitReservation(): void {
    if (this.reservationForm.invalid || !this.house || !this.authService.isLoggedIn()) {
      return;
    }
    const reservationData = {
      ...this.reservationForm.value,
      housingCode: this.house.code,// Implement this in your AuthService
      housingName: this.house.name,
      clientDni: this.clientDni,
      valorated:false
    };

    this.reservationService.createReservation(reservationData).subscribe({
      next: () => {
        Swal.fire({
          title: '¡Reserva exitosa! 🎉',
          text: 'Tu reserva fue creada correctamente.',
          icon: 'success',
          confirmButtonText: '¡Genial!',
          backdrop: true,
          showClass: {
            popup: 'animate__animated animate__fadeInDown'
          },
          hideClass: {
            popup: 'animate__animated animate__fadeOutUp'
          }
        });
        this.reservationForm.reset();
      },
      error: (err: any) => {
        console.error('Error creating reservation:', err);
        Swal.fire({
          title: 'Oops 😬',
          text: 'Hubo un error al crear la reserva.',
          icon: 'error',
          confirmButtonText: 'Intentar de nuevo'
        });
    
      }
    });
  }

  onSubmitComment(): void {
    if (this.commentForm.invalid || !this.house || !this.authService.isLoggedIn()) {
      return;
    }

    const commentData = {
      ...this.commentForm.value,
      hotelCode: this.house.code,
      userDni: this.clientDni,
      userName: this.clientName
    };

    this.reviewService.createReview(commentData).subscribe({
      next: () => {
        Swal.fire({
          title: 'Comentario creado de manera exitosa! 🎉',
          text: 'Tu comentario fue creado correctamente.',
          icon: 'success',
          confirmButtonText: '¡Genial!',
          backdrop: true,
          showClass: {
            popup: 'animate__animated animate__fadeInDown'
          },
          hideClass: {
            popup: 'animate__animated animate__fadeOutUp'
          }
        });
        this.commentForm.reset();
        this.loadComments(this.house!.code, 1); // Reload comments
      },
      
      error: (err: any) => {
        Swal.fire({
          title: 'Oops 😬',
          text: 'Hubo un error al crear el comentario.',
          icon: 'error',
          confirmButtonText: 'Intentar de nuevo'
        });
        console.error('Error submitting comment:', err);
      }
    });
  }

  loadMoreComments(): void {
    if (this.house) {
      this.loadComments(this.house.code, this.currentPage + 1);
    }
  }

  getRatingWidth(rating: number): string {
    return `${rating}%`;
  }
  getRatingClass(rating: number): string {
    if (rating < 25) {
      return 'poor';
    } else if (rating < 50) {
      return 'average';
    } else if (rating < 75) {
      return 'good';
    } else {
      return 'excellent';
    }
  }
}
