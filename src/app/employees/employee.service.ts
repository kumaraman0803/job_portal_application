// employee.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EmployeesComponent } from './employees.component';
import { Employee } from './employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employees: Employee[] = [];
  private employeeList = new BehaviorSubject<Employee[]>([]);

  constructor() { }

  getEmployees(): Observable<Employee[]> {
    return this.employeeList.asObservable();
  }

  addEmployee(employee: Employee): void {
    this.employees.push(employee);
    this.employeeList.next([...this.employees]);
  }

  updateEmployee(index: number, employee: Employee): void {
    this.employees[index] = employee;
    this.employeeList.next([...this.employees]);
  }

  deleteEmployee(index: number): void {
    this.employees.splice(index, 1);
    this.employeeList.next([...this.employees]);
  }
}
