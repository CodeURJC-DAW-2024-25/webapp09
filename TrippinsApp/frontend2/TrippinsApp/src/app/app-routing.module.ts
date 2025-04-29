import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomComponent } from './components/room/room.component';
import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin.component';
import { ErrorComponent } from './components/error/error.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { NewhotelComponent } from './components/newhotel/newhotel.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { authGuard } from './guards/auth.guard';
import { roleGuard } from './guards/role.guard';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  { path: 'index', redirectTo: '', pathMatch: 'full' },
  
  // Public routes
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'rooms', component: RoomComponent },
  { path: 'rooms/:id', component: RoomDetailsComponent,canActivate: [authGuard] },
  
  // Protected user routes
  { 
    path: 'profile', 
    component: ProfileComponent,
    canActivate: [authGuard]
  },
  
  // Admin routes
  { 
    path: 'admin', 
    component: AdminComponent,
    canActivate: [authGuard, roleGuard],
    data: {roles: ["ROLE_ADMIN"]}
  },
  { 
    path: 'housing/new', 
    component: NewhotelComponent,
    canActivate: [authGuard]
  },
  
  // Error route
  { path: 'error', component: ErrorComponent },
  
  // Wildcard route (must be last)
  { path: '**', redirectTo: 'error' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
