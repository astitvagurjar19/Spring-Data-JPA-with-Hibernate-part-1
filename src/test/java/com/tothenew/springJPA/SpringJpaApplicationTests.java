package com.tothenew.springJPA;

import com.tothenew.springJPA.entity.Employee;
import com.tothenew.springJPA.repos.EmployeeRepository;
import com.tothenew.springJPA.repos.PagingRepository;
import net.bytebuddy.TypeCache;
import org.assertj.core.internal.bytebuddy.matcher.MethodSortMatcher;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class SpringJpaApplicationTests {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private PagingRepository pagingRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreate() {
        Employee employee = new Employee();
        employee.setName("Astitva");
        employee.setAge(22);
        employee.setLoc("Bhopal");

        repository.save(employee);
    }

    @Test
    void testUpdate() {
        Employee employee = repository.findById(1).get();
        employee.setLoc("Noida");
        repository.save(employee);
    }

    @Test
    void testDelete() {
        if (repository.existsById(1))
            repository.deleteById(1);
    }

    @Test
    void testRead() {
        Employee employee = repository.findById(1).get();
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Astitva", employee.getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> " + employee.getAge());
    }

    @Test
    void testCount() {
        System.out.println("Total Employees : " + repository.count());
    }

    @Test
    void findByName() {
        List<Employee> employees = repository.findByName("Astitva");
        employees.forEach(employee -> System.out.println(employee.getName() + ", " + employee.getAge() + ", " + employee.getLoc()));
    }

    @Test
    void findByNameContains(){
        List<Employee> employees = repository.findByNameContains("A");
        employees.forEach(employee -> System.out.println(employee.getName() + ", " + employee.getAge() + ", " + employee.getLoc()));
    }

    @Test
    void findByAgeBetween(){
        List<Employee> employees = repository.findByAgeBetween(28,32);
        employees.forEach(employee -> System.out.println(employee.getName() + ", " + employee.getAge() + ", " + employee.getLoc()));
    }

    @Test
    void testFindAllPaging(){
        Pageable pageable = PageRequest.of(0,1);
        Page<Employee> page = pagingRepository.findAll(pageable);
        page.forEach(employee -> System.out.println(employee.getName() + ", " + employee.getAge() + ", " + employee.getLoc()));
    }

    @Test
    void testFindAllSorting(){
        pagingRepository.findAll(Sort.by("age")).forEach(employee -> System.out.println(employee.getName() + ", " + employee.getAge() + ", " + employee.getLoc()));
    }
}
