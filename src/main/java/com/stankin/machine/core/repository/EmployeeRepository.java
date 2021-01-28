package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT COUNT(*) FROM employees WHERE location_id = :location_id")
    int findCountEmpByLocationId(@Param("location_id") Long locationId);
}
