package com.org.ems.service;

import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EMSServiceImplTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EMSServiceImpl service;

    @Test
    void testEmployeeEarningsLessThanThreshold() {
        double salary = 56893;
        List<EmployeeDTO> list = List.of(new EmployeeDTO(1, "Alex", "IT", 56000));
        when(employeeService.getEmployeesWithLessSalaryThan(salary)).thenReturn(list);

        List<EmployeeDTO> actualList = service.getEmployeesWithLessSalaryThan(salary);

        assertEquals(actualList, list);

        verify(employeeService).getEmployeesWithLessSalaryThan(salary);
    }

    @Test
    void testEmployeeEarningsGreaterThanThreshold() {
        double salary = 50000;
        List<EmployeeDTO> list = List.of(new EmployeeDTO(1, "Alex", "IT", 56000));
        when(employeeService.getEmployeesWithGreaterSalaryThan(salary)).thenReturn(list);

        List<EmployeeDTO> actualList = service.getEmployeesWithGreaterSalaryThan(salary);

        assertEquals(actualList, list);

        verify(employeeService).getEmployeesWithGreaterSalaryThan(salary);
    }

    @Test
    void testGetAllEmployee() {
        Employee employee1 = Employee.builder().employeeID(1).department(Department.builder().id(1).name("IT").build()).name("Alex").build();
        Employee employee2 = Employee.builder().employeeID(2).department(Department.builder().id(1).name("IT").build()).name("Will").build();

        List<Employee> employees = List.of(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> actualList = service.getAllEmployees();

        assertEquals(actualList, employees);
    }
}
