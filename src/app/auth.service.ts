import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  login(username: string, password: string): Observable<boolean> {
    // In a real application, this method would send a request to the server to verify credentials
    // For simplicity, we'll just use a hardcoded check
    if (username === 'admin' && password === 'password') {
      // Authentication successful
      localStorage.setItem('isLoggedIn', 'true');
      return of(true);
    } else {
      // Authentication failed
      return of(false);
    }
  }

  logout(): void {
    // Clear authentication status
    localStorage.removeItem('isLoggedIn');
  }

  isLoggedIn(): boolean {
    // Check authentication status
    return localStorage.getItem('isLoggedIn') === 'true';
  }
}
