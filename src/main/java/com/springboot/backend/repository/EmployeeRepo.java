package com.springboot.backend.repository;

import com.springboot.backend.model.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo {
    @Query("select * from employees")
    public List<Employee> getAllEmployeeViaQuery();
}
