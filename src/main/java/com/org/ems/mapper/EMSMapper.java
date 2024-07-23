package com.org.ems.mapper;

import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.EmployeeRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EMSMapper {

    public List<EmployeeDTO> map(@NotNull List<Employee> employees) {
        return employees.stream()
                .map(e -> new EmployeeDTO(e.getEmployeeID(), e.getName(), e.getDepartment().getName(), e.getSalary()))
                .toList();
    }

    public Employee map(@NotNull EmployeeRequest request, @NotNull Department department) {
        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());

        return employee;
    }
}