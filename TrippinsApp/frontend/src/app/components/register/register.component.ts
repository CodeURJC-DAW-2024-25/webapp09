import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import Swal from 'sweetalert2';
import { UserServiceService } from '../../services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  standalone:false
})
export class RegisterComponent {
  registerForm: FormGroup;
  passwordMatch = true;
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userService: UserServiceService
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      dni: ['', [
        Validators.required,
        Validators.pattern(/^[0-9]{8}[A-Z]$/),
        Validators.maxLength(9)
      ]],
      number: ['', [Validators.required]],
      password: ['', [
        Validators.required,
        Validators.minLength(8)
      ]],
      password2: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(control: AbstractControl) {
    const password = control.get('password')?.value;
    const password2 = control.get('password2')?.value;
    return password === password2 ? null : { mismatch: true };
  }

  onSubmit() {
    if (this.registerForm.invalid || this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;
    const formData = this.registerForm.value;

    this.userService.createAccount({
      name: formData.name,
      email: formData.email,
      dni: formData.dni,
      number: formData.number,
      password: formData.password
    }).subscribe({
      next: () => {
        Swal.fire({
          title: '¡Cuenta creada!',
          text: 'Tu cuenta fue creada con éxito.',
          icon: 'success',
          confirmButtonText: '¡Genial!',
          confirmButtonColor: '#3085d6'
        });
        this.router.navigate(['new/login'], {
          queryParams: { registered: 'true' }
        });
      },
      error: (err) => {
        Swal.fire({
          title: 'Error',
          text: 'No se pudo crear la cuenta. Verifica los datos e intenta nuevamente.',
          icon: 'error',
          confirmButtonText: 'Entendido',
          confirmButtonColor: '#d33'
        });
        console.error('Registration error:', err);
        this.isSubmitting = false;
      }
    });
  }

  get f() {
    return this.registerForm.controls;
  }
}
