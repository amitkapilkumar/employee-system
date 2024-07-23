package com.org.ems.controller;

import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.DepartmentRequest;
import com.org.ems.model.request.EmployeeRequest;
import com.org.ems.model.response.DepartmentResponse;
import com.org.ems.model.response.EmployeeResponse;
import com.org.ems.service.EMSService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/ems")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EMSController {
    private final EMSService emsService;

    @Operation(summary = "Retrieves all the employees based on department")
    @GetMapping("/employees/department/{name}")
    public List<EmployeeDTO> getEmployees(@PathVariable(name = "name") String name) {
        return emsService.getEmployees(name);
    }

    @Operation(summary = "Retrieves all the employees with earnings less than {salary} param")
    @GetMapping("/employees/earning/less/{salary}")
    public List<EmployeeDTO> getEmployeesWithLessSalary(@PathVariable(name = "salary") double salary) {
        return emsService.getEmployeesWithLessSalaryThan(salary);
    }

    @Operation(summary = "Retrieves all the employees with earnings greater than {salary} param")
    @GetMapping("/employees/earning/greater/{salary}")
    public List<EmployeeDTO> getEmployeesWithGreaterSalary(@PathVariable(name = "salary") double salary) {
        return emsService.getEmployeesWithGreaterSalaryThan(salary);
    }

    @Operation(summary = "List All employees with associated departments")
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return emsService.getAllEmployees();
    }

    @Operation(summary = "Get list of All department")
    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return emsService.getAllDepartments();
    }

    @Operation(summary = "Get department based on Id")
    @GetMapping("/department/{department_id}")
    public Department getDepartment(@PathVariable(name = "department_id") int id) {
        return emsService.getDepartment(id);
    }

    @Operation(summary = "Create a new department")
    @PostMapping("/department")
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        Department department = emsService.createDepartment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(DepartmentResponse.builder()
                .message("Department created Successfully").department(department).build());
    }

    @Operation(summary = "Create a new employee")
    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee employee = emsService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponse.builder()
                .message("Employee created Successfully").employee(employee).build());
    }
}
