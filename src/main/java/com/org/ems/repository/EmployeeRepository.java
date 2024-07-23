package com.org.ems.repository;

import com.org.ems.model.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>  {

    @Query("select e from Employee e where e.department.id = ?1")
    Iterable<Employee> getEmployeesByDepartment(int id);

    Iterable<Employee> getEmployeeByDepartment_Id(int id);

    Employee getByName(String name);

    @Query("select e from Employee e where e.salary < ?1")
    Iterable<Employee> getEmployeesWithLessSalaryThan(double salary);

    @Query("select e from Employee e where e.salary > ?1")
    Iterable<Employee> getEmployeesWithGreaterSalaryThan(double salary);
}
