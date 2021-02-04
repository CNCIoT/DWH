package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Plan;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    List<Plan> findAll();

    @Query("SELECT actual_date FROM mdc.plans WHERE location_id = :location_id")
    List<Date> findByLocation(@Param("location_id") Long locationId);
}
