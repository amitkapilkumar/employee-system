package com.org.ems.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ems.exception.DepartmentNotFoundException;
import com.org.ems.model.dto.EmployeeDTO;
import com.org.ems.model.entity.Department;
import com.org.ems.model.entity.Employee;
import com.org.ems.model.request.DepartmentRequest;
import com.org.ems.model.request.EmployeeRequest;
import com.org.ems.model.response.DepartmentResponse;
import com.org.ems.model.response.EmployeeResponse;
import com.org.ems.service.EMSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EMSControllerTest {
    @Mock
    private EMSService service;

    @InjectMocks
    private EMSController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new EMSControllerAdvice())
                .build();
    }

    @Test
    void testGetEmployeesUnderDepartment() throws Exception {
        String department = "IT" ;
        EmployeeDTO employee = new EmployeeDTO(1, "Alex", "IT", 5000);

        when(service.getEmployees(department)).thenReturn(List.of(employee));

        MvcResult result = mockMvc.perform(get("/api/v1/ems/employees/department/" + department)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<EmployeeDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals( 1, response.size());
        assertEquals(response.get(0), employee);

        verify(service).getEmployees(department);
    }

    @Test
    void testGetEmployeesUnderDepartment_WithException() throws Exception {
        String department = "IT";

        when(service.getEmployees(department)).thenThrow(new DepartmentNotFoundException("Department not found"));

       mockMvc.perform(get("/api/v1/ems/employees/department/" + department)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).getEmployees(department);
    }

    @Test
    void testEmployeesWithEarningsLessThan_BAD_REQUEST() throws Exception {
        mockMvc.perform(get("/api/v1/ems/employees/earning/less/a")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmployeesWithEarningsLessThan() throws Exception {
        double salary = 50000;
        EmployeeDTO employee = new EmployeeDTO(1, "Alex", "IT", 45000);

        when(service.getEmployeesWithLessSalaryThan(salary)).thenReturn(List.of(employee));

        MvcResult result = mockMvc.perform(get("/api/v1/ems/employees/earning/less/" + salary)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<EmployeeDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals( 1, response.size());
        assertEquals(response.get(0), employee);

        verify(service).getEmployeesWithLessSalaryThan(salary);
    }

    @Test
    void testEmployeesWithEarningsGreaterThan_BAD_REQUEST() throws Exception {
        mockMvc.perform(get("/api/v1/ems/employees/earning/greater/a")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmployeesWithEarningsGreaterThan() throws Exception {
        double salary = 50000;
        EmployeeDTO employee = new EmployeeDTO(1, "Alex", "IT", 60000);

        when(service.getEmployeesWithGreaterSalaryThan(salary)).thenReturn(List.of(employee));

        MvcResult result = mockMvc.perform(get("/api/v1/ems/employees/earning/greater/" + salary)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<EmployeeDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals( 1, response.size());
        assertEquals(response.get(0), employee);

        verify(service).getEmployeesWithGreaterSalaryThan(salary);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = Employee.builder().employeeID(1).department(Department.builder().id(1).name("IT").build()).name("Alex").build();
        Employee employee2 = Employee.builder().employeeID(2).department(Department.builder().id(1).name("IT").build()).name("Will").build();

        when(service.getAllEmployees()).thenReturn(List.of(employee1, employee2));

        MvcResult result = mockMvc.perform(get("/api/v1/ems/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<Employee> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertEquals(2, response.size());
        assertEquals(response.get(0), employee1);
        assertEquals(response.get(1), employee2);

        verify(service).getAllEmployees();
    }

    @Test
    void testGetAllDepartment() throws Exception {
        Department department1 = Department.builder().id(1).name("IT").build();
        Department department2 = Department.builder().id(1).name("HR").build();

        when(service.getAllDepartments()).thenReturn(List.of(department1, department2));

        MvcResult result = mockMvc.perform(get("/api/v1/ems/departments").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<Department> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertEquals(2, response.size());
        assertEquals(response.get(0), department1);
        assertEquals(response.get(1), department2);

        verify(service).getAllDepartments();
    }

    @Test
    void testGetDepartment() throws Exception {
        Department department = Department.builder().id(1).name("IT").build();

        when(service.getDepartment(1)).thenReturn(department);

        MvcResult result = mockMvc.perform(get("/api/v1/ems/department/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Department response = objectMapper.readValue(result.getResponse().getContentAsString(), Department.class);

        assertEquals(response, department);

        verify(service).getDepartment(1);
    }

    @Test
    void testCreateEmployee_Exception_400() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder().build();

        mockMvc.perform(post("/api/v1/ems/employee")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder().departmentId(2).name("Tim").salary(408349).build();
        Employee employee = Employee.builder().build();

        when(service.createEmployee(request)).thenReturn(employee);

        MvcResult result = mockMvc.perform(post("/api/v1/ems/employee")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated()).andReturn();

        EmployeeResponse response = objectMapper.readValue(result.getResponse().getContentAsString(),
                EmployeeResponse.class);

        assertEquals(response.getEmployee(), employee);

        verify(service).createEmployee(request);
    }

    @Test
    void testCreateDepartment() throws Exception {
        DepartmentRequest request = DepartmentRequest.builder().name("Accounts").build();
        Department department = Department.builder().build();

        when(service.createDepartment(request)).thenReturn(department);

        MvcResult result = mockMvc.perform(post("/api/v1/ems/department")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated()).andReturn();

        DepartmentResponse response = objectMapper.readValue(result.getResponse().getContentAsString(),
                DepartmentResponse.class);

        assertNotNull(response.getMessage());
        assertEquals(department, response.getDepartment());

        verify(service).createDepartment(request);
    }

    @Test
    void testCreateDepartment_Exception_400() throws Exception {
        DepartmentRequest request = DepartmentRequest.builder().build();

        mockMvc.perform(post("/api/v1/ems/department")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());

    }
}
