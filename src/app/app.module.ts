import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { EmployeesComponent } from './employees/employees.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    EmployeesComponent,
    UserComponent,
    AdminComponent,
    FeedbackComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
