package com.springboot.backend.service.impl;

import com.springboot.backend.model.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();

    Employee getEmployeeById(Long Id);

    Employee updateEmployee(Employee employee, Long Id);

    Boolean deleteEmployee(Long Id);
}
