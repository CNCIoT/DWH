package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT COUNT(*) FROM employees WHERE location_id = :location_id")
    int findCountEmpByLocationId(@Param("location_id") Long locationId);

    List<Employee> findAll();

    @Query("SELECT * FROM mdc.employees ex\n" +
            "WHERE (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp) \n" +
            "      AND created_at <= COALESCE(:end_date, now()))")
    List<Employee> findAllByStartAndEndDate(@Param("start_date") Date startDate, @Param("end_date") Date endDate);
}
