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
  { path: 'new/home', redirectTo: '', pathMatch: 'full' },
  { path: 'new/index', redirectTo: '', pathMatch: 'full' },
  
  // Public routes
  { path: 'new/about', component: AboutComponent },
  { path: 'new/login', component: LoginComponent },
  { path: 'new/register', component: RegisterComponent },
  { path: 'new/rooms', component: RoomComponent },
  { path: 'new/rooms/:id', component: RoomDetailsComponent,canActivate: [authGuard] },
  
  // Protected user routes
  { 
    path: 'new/profile', 
    component: ProfileComponent,
    canActivate: [authGuard]
  },
  
  // Admin routes
  { 
    path: 'new/admin', 
    component: AdminComponent,
    canActivate: [authGuard, roleGuard],
    data: {roles: ["ROLE_ADMIN"]}
  },
  { 
    path: 'new/housing/creation', 
    component: NewhotelComponent,
    canActivate: [authGuard]
  },
  
  // Error route
  { path: 'new/error', component: ErrorComponent },
  
  // Wildcard route (must be last)
  { path: '**', redirectTo: 'error' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
