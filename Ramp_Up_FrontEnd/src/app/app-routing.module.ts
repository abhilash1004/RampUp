import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsumerRegisterComponent } from './consumer-register/consumer-register.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SellerRegisterComponent } from './seller-register/seller-register.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';

const routes: Routes = [
  { path:  'home', component:  HomeComponent},
  { path:  'register', component:  UserRegistrationComponent},
  { path:  'login', component:  LoginComponent},
  { path:  'consumerRegister', component:  ConsumerRegisterComponent},
  { path:  'sellerRegister', component:  SellerRegisterComponent},
  { path:  '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
