import { Component } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-newhotel',
  templateUrl: './newhotel.component.html',
  styleUrl: './newhotel.component.css',
  standalone:false
})
export class NewhotelComponent {
  houseForm: FormGroup;
  starsValue = 0;
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
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
    
    const formData = new FormData();
    formData.append('name', this.houseForm.get('name')?.value);
    formData.append('location', this.houseForm.get('location')?.value);
    formData.append('price', this.houseForm.get('price')?.value);
    formData.append('description', this.houseForm.get('description')?.value);
    formData.append('image', this.houseForm.get('image')?.value);
    formData.append('tags', this.houseForm.get('tags')?.value);
    formData.append('stars', this.houseForm.get('stars')?.value);

    this.http.post(`${environment.baseUrlApi}/v1/api/houses`, formData)
      .subscribe({
        next: () => {
          this.router.navigate(['/success']); // Redirect to success index
        },
        error: (err) => {
          console.error('Error creating house:', err);
          this.isSubmitting = false;
        }
      });
  }
}
