import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { OderComponent } from './components/oder/oder.component';
import { OderConfirmComponent } from './components/oder-confirm/oder-confirm.component';
import { RegisterComponent } from './components/register/register.component';
import { DetailProductComponent } from './components/detail-product/detail-product.component';


@NgModule({
  imports: [
    AppModule,
    ServerModule,
  ],
  bootstrap: [
    // AppModule,
   // HomeComponent,
    // OderComponent,
    // OderConfirmComponent,
    LoginComponent,
    // RegisterComponent,
    // DetailProductComponent
  ],
})
export class AppServerModule {}
