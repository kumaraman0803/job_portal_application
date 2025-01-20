import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployerService {
  private apiUrl = 'http://localhost:8089/employer';

  constructor(private http: HttpClient) {}

  registerEmployer(employerData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, employerData);
  }
}
