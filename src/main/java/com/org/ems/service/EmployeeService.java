package com.org.ems.service;

import com.org.ems.mapper.EMSMapper;
import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.EmployeeRequest;
import com.org.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EMSMapper mapper;

    public List<EmployeeDTO> getEmployees(int departmentId) {
        return mapEmployeesDTOs(repository.getEmployeeByDepartment_Id(departmentId));
    }

    public List<EmployeeDTO> getEmployeesWithLessSalaryThan(double salary) {
        return mapEmployeesDTOs(repository.getEmployeesWithLessSalaryThan(salary));
    }

    private List<EmployeeDTO> mapEmployeesDTOs(Iterable<Employee> employeeIterable) {

        List<Employee> employees = StreamSupport.stream(employeeIterable.spliterator(), false).toList();

        return mapper.map(employees);
    }

    public List<EmployeeDTO> getEmployeesWithGreaterSalaryThan(double salary) {
        return mapEmployeesDTOs(repository.getEmployeesWithGreaterSalaryThan(salary));
    }

    public List<Employee> getAllEmployees() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    public Employee createEmployee(EmployeeRequest request, Department department) {
        Employee employee = mapper.map(request, department);
        repository.save(employee);
        return employee;
    }
}
