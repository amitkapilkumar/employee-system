package com.org.ems.service;

import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.DepartmentRequest;
import com.org.ems.model.request.EmployeeRequest;

import java.util.List;

public interface EMSService {

    List<EmployeeDTO> getEmployees(String departmentName);

    List<EmployeeDTO> getEmployeesWithLessSalaryThan(double salary);

    List<EmployeeDTO> getEmployeesWithGreaterSalaryThan(double salary);

    List<Employee> getAllEmployees();

    List<Department> getAllDepartments();

    Department getDepartment(int id);

    Department createDepartment(DepartmentRequest request);

    Employee createEmployee(EmployeeRequest request);
}
