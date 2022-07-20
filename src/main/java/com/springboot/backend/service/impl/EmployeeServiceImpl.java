package com.springboot.backend.service.impl;


import com.springboot.backend.exceptions.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepo;
import com.springboot.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    private EmployeeRepository employeeRepository;
    private EmployeeRepo employeeRepo;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        // below db access via query doesn't work in hibernate.. You need HQL for this kind of use-case
//        return employeeRepo.getAllEmployeeViaQuery();
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long Id) {
        Optional<Employee> employee = employeeRepository.findById(Id);
        if (employee.isPresent())
            return employee.get();
        else
            throw new ResourceNotFoundException("Employee", "", Id);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee currentEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Employee.class.getName(), "Id", id)
        );

        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(currentEmployee);
    }

    @Override
    public Boolean deleteEmployee(Long Id) {
        if (employeeRepository.existsById(Id)) {
            employeeRepository.deleteById(Id);
            return true;
        } else {
            throw new ResourceNotFoundException(Employee.class.getName(),"Id", Id);
        }
    }
}
