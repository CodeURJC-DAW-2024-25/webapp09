import { Component } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { HousingServiceService } from '../../services/housing-service.service';

@Component({
  selector: 'app-newhotel',
  templateUrl: './newhotel.component.html',
  styleUrl: './newhotel.component.css',
  standalone: false
})
export class NewhotelComponent {
  houseForm: FormGroup;
  starsValue = 0;
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private housingService: HousingServiceService
  ) {
    this.houseForm = this.fb.group({
      name: ['', Validators.required],
      location: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      description: ['', Validators.required],
      image: [null, Validators.required],
      tags: [''],
      stars: [0, [Validators.min(0), Validators.max(5)]]
    });
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.houseForm.patchValue({
        image: file
      });
    }
  }

  updateStarsValue(event: any) {
    this.starsValue = event.target.value;
    this.houseForm.patchValue({
      stars: this.starsValue
    });
  }

  onSubmit() {
    if (this.houseForm.invalid || this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;

    const rawTags = this.houseForm.get('tags')?.value;
    const tagList = rawTags
      .split(',')
      .map((tag: string) => tag.trim())
      .filter((tag: string) => tag.length > 0)
      .map((tag: string) => ({ id: tag }));

    const houseData = {
      name: this.houseForm.get('name')?.value,
      location: this.houseForm.get('location')?.value,
      price: this.houseForm.get('price')?.value,
      description: this.houseForm.get('description')?.value,
      tags: tagList,
      stars: this.houseForm.get('stars')?.value,
      acepted:false
    };

    this.housingService.createRoom(houseData)
      .subscribe({
        next: (createdHouse) => {
          if (this.houseForm.get('image')?.value) {
            this.uploadImage(createdHouse.code);
          } else {
            this.finishSuccess();
          }

        },
        error: (err) => {
          Swal.fire({
            title: 'Oops 😬',
            text: 'Hubo un error al crear el alojamiento.',
            icon: 'error',
            confirmButtonText: 'Intentar de nuevo'
          });
          console.error('Error creating house:', err);
          this.isSubmitting = false;
        }
      });
  }
  uploadImage(houseId: number): void {
    
    this.housingService.uploadHousingImage(houseId, this.houseForm.get('image')?.value).subscribe({
      next: () => this.finishSuccess(),
      error: (err: any) => this.handleError(err)
    });
  }


  finishSuccess() {
    this.isSubmitting = false;
    Swal.fire('¡Éxito!', 'Hotel creado correctamente.', 'success');
  }
  
  handleError(err: any) {
    this.isSubmitting = false;
    console.error('Error:', err);
    Swal.fire('Error', 'Algo salió mal.', 'error');
  }
}
