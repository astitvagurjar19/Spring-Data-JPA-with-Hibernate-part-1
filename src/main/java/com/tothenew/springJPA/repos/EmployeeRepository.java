package com.tothenew.springJPA.repos;

import com.tothenew.springJPA.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByNameContains(String strings);
    List<Employee> findByAgeBetween(int value1,int value2);
}

