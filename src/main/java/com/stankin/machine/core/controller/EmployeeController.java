package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> save(@NotNull @RequestBody Employee employee) {
        Employee newEmployee = employeeService.save(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@NotNull @PathVariable("id") Long id) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        return employeeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull Employee employee) {
        employeeService.delete(employee);
        return ResponseEntity.noContent().build();
    }
}
