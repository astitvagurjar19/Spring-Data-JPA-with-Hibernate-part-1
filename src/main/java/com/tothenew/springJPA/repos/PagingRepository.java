package com.tothenew.springJPA.repos;

import com.tothenew.springJPA.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingRepository extends PagingAndSortingRepository<Employee,Integer> {
}
