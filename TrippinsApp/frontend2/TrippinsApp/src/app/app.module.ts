import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { IndexComponent } from './components/index/index.component';
import { RoomComponent } from './components/room/room.component';
import { AdminComponent } from './components/admin/admin.component';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/login/login.component';
import { NewhotelComponent } from './components/newhotel/newhotel.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { ReservationPanelComponent } from './components/admin/reservation-panel/reservation-panel.component';
import { HousingPanelComponent } from './components/admin/housing-panel/housing-panel.component';
import { TestimonialsComponent } from './components/testimonials/testimonials.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    HeaderComponent,
    FooterComponent,
    IndexComponent,
    RoomComponent,
    AdminComponent,
    ErrorComponent,
    LoginComponent,
    NewhotelComponent,
    ProfileComponent,
    RegisterComponent,
    RoomDetailsComponent,
    ReservationPanelComponent,
    HousingPanelComponent,
    TestimonialsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
