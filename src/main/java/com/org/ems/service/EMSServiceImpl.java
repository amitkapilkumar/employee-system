package com.org.ems.service;

import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.DepartmentRequest;
import com.org.ems.model.request.EmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EMSServiceImpl implements EMSService {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public List<EmployeeDTO> getEmployees(String departmentName) {
        Department department = departmentService.getDepartment(departmentName);
        return employeeService.getEmployees(department.getId());
    }

    public List<EmployeeDTO> getEmployeesWithLessSalaryThan(double salary) {
        return employeeService.getEmployeesWithLessSalaryThan(salary);
    }

    public List<EmployeeDTO> getEmployeesWithGreaterSalaryThan(double salary) {
        return employeeService.getEmployeesWithGreaterSalaryThan(salary);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    public Department getDepartment(int id) {
        return departmentService.getDepartment(id);
    }

    public Department createDepartment(DepartmentRequest request) {
        return departmentService.createDepartment(request);
    }

    public Employee createEmployee(EmployeeRequest request) {
        Department department = departmentService.getDepartment(request.getDepartmentId());
        return employeeService.createEmployee(request, department);
    }
}
