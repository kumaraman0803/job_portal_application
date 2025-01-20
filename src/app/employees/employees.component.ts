import { Component } from '@angular/core';
import { EmployeeService } from './employee.service';
import { Employee } from './employee.model';


@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent {
  employees: Employee[] = [];
  newEmployee: Employee = { id: 0, name: '', position: '' };
  editingIndex: number = -1;
  editingEmployee: Employee = { id: 0, name: '', position: '' };

  constructor(private employeeService: EmployeeService) {
    this.employeeService.getEmployees().subscribe(employees => {
      this.employees = employees;
    });
  }

  addEmployee() {
    this.employeeService.addEmployee(this.newEmployee);
    this.newEmployee = { id: 0, name: '', position: '' }; // Clear form
  }

  updateEmployee(index: number) {
    this.employeeService.updateEmployee(index, this.editingEmployee);
    this.editingIndex = -1;
    this.editingEmployee = { id: 0, name: '', position: '' };
  }

  deleteEmployee(index: number) {
    this.employeeService.deleteEmployee(index);
  }

  startEditing(index: number, employee: Employee) {
    this.editingIndex = index;
    this.editingEmployee = { ...employee };
  }

  cancelEditing() {
    this.editingIndex = -1;
    this.editingEmployee = { id: 0, name: '', position: '' };
  }
  

}
