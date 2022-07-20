package com.springboot.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.backend.model.Employee;
import com.springboot.backend.service.impl.EmployeeServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class EmployeeController{
    private EmployeeServiceInterface employeeService;

    public EmployeeController(EmployeeServiceInterface employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @PostMapping("addEmployee")
    public ResponseEntity<Employee> saveEmployee(
            @RequestBody Employee employee) {

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("getAllEmployees")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
    public ResponseEntity<List<Employee>> getAllEmployees() {
//        return employeeService.getAllEmployee();
//        List<Employee> allEmployees = employeeService.getAllEmployee();
//        String listOfJson;
//        try {
//            listOfJson = new ObjectMapper().writeValueAsString(allEmployees);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
//        return allEmployees;
    }

    // URL: http://localhost:8080/api/getEmployee/1
    @GetMapping("getEmployee/{id}")
    public ResponseEntity<Employee> saveEmployee(
            @PathVariable("id") Long id) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable("id") Long id) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public ResponseEntity<Boolean> deleteEmployee(
            @PathVariable("id") Long id) {
        return new ResponseEntity<Boolean>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
