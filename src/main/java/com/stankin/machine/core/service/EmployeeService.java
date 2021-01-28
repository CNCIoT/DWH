package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee save(@NotNull Employee employee) {
        log.trace(">>save... employee={}", employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        if (employeeOptional.isPresent()) {
            log.trace("update existing employee");
            return employeeRepository.save(employee);
        }
        log.trace("create new employee");
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return employeeRepository.findById(id);
    }

    public void delete(@NotNull Employee employee) {
        log.trace(">>delete... employee={}", employee);
        employeeRepository.delete(employee);
    }

    public int findCountEmpByLocationId(@NotNull Long locationId){
        log.trace("findCountEmpByLocationId... locationId={}", locationId);
        return employeeRepository.findCountEmpByLocationId(locationId);
    }

}
