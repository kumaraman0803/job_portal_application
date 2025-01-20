import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { EmployeesComponent } from './employees/employees.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  
  // { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login page by default
  // { path: 'login', component: LoginComponent },
  { path: 'login', component: LoginComponent },
   { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login by default
  //{ path: '**', redirectTo: '/login' },
   { path: 'home', component: HomeComponent },
  { path: 'employees', component: EmployeesComponent },
  { path: 'user', component: UserComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'feedback', component: FeedbackComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
