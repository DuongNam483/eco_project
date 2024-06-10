import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { OderComponent } from './components/oder/oder.component';
import { OderConfirmComponent } from './components/oder-confirm/oder-confirm.component';
import { RegisterComponent } from './components/register/register.component';
import { DetailProductComponent } from './components/detail-product/detail-product.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HttpHeaders, withFetch, provideHttpClient, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenInterceptor } from './interceptors/token.interceptor';
@NgModule({
  declarations: [
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    OderComponent,
    OderConfirmComponent,
    LoginComponent,
    RegisterComponent,
    DetailProductComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [
    // HomeComponent,
    // OderComponent,
    // OderConfirmComponent,
    LoginComponent,
    // RegisterComponent,
    // DetailProductComponent
  ]
})
export class AppModule { }
