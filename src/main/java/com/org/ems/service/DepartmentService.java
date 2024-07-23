package com.org.ems.service;

import com.org.ems.exception.DepartmentNotFoundException;
import com.org.ems.model.entity.Department;
import com.org.ems.model.request.DepartmentRequest;
import com.org.ems.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.org.ems.util.AppUtil.DEPARTMENT_NOT_FOUND_MESSAGE;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;

    public List<Department> getAllDepartments(){
        return (List<Department>) repository.findAll();
    }

    public Department getDepartment(int id) {
        Iterable<Department> department = repository.findDepartment(id);
        if (department.iterator().hasNext()) {
            return department.iterator().next();
        }
        else {
            throw new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND_MESSAGE.replace("<id>", String.valueOf(id)));
        }
    }

    public Department getDepartment(String name) {
        Iterable<Department> department = repository.findDepartment(name);
        if (department.iterator().hasNext()) {
            return department.iterator().next();
        }
        else {
            throw new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND_MESSAGE.replace("<name>", String.valueOf(name)));
        }
    }

    public Department createDepartment(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        repository.save(department);
        return department;
    }
}