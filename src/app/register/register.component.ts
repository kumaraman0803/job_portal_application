import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmployerService } from './employer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
registerEmployer() {
throw new Error('Method not implemented.');
}
  // Define properties for form fields
  name: string = '';
  orgName: string = '';
  contactNo: string = '';
  email: string = '';
  address: string = '';
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private employerService: EmployerService) {}

  register(): void {
    // Create an object with the form data
    const employerData = {
      name: this.name,
      orgName: this.orgName,
      contactNo: this.contactNo,
      email: this.email,
      address: this.address,
      username: this.username,
      password: this.password
    };

    // Call the registerEmployer method from the employer service
    this.employerService.registerEmployer(employerData).subscribe(
      response => {
        // Registration successful, navigate to login page
        this.router.navigate(['/login']);
      },
      error => {
        // Registration failed, display error message
        this.errorMessage = error;
      }
    );
  }
}
