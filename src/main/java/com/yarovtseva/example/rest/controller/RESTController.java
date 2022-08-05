package com.yarovtseva.example.rest.controller;

import com.yarovtseva.example.rest.exception_handling.NoSuchEmployeeException;
import com.yarovtseva.example.rest.model.Employee;
import com.yarovtseva.example.rest.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final EmployeeService employeeService;

    public RESTController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new RuntimeException(String.format("There is no employee with ID %d in database", id));
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException(String.format("There is no employee with ID %d in database", id));
        }
        employeeService.deleteEmployee(id);
        return String.format("Employee with ID %d was deleted", id);
    }
}
