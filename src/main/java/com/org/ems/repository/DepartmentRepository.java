package com.org.ems.repository;

import com.org.ems.model.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    @Query("select d from Department d where d.id = ?1")
    Iterable<Department> findDepartment(int id);

    @Query("select d from Department d where d.name = ?1")
    Iterable<Department> findDepartment(String name);
}