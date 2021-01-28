package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
