import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    this.authService.login(this.username, this.password).subscribe(
      success => {
        if (success) {
          // Navigate to the home page or any other desired page upon successful login
          this.router.navigate(['/home']);
        } else {
          this.errorMessage = 'Invalid username or password';
        }
      }
    );
  }

  register(): void {
    // Redirect to the register page
    this.router.navigate(['/register']);
  }
}
